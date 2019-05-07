package controller

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}
import akka.io.Tcp.Connected
import akka.io.{IO, Tcp}
import akka.util.ByteString
import play.api.libs.json.{JsValue, Json}

class GameServer(gameActor: ActorRef) extends Actor {
  import Tcp._
  import context.system

  var webServers: Set[ActorRef] = Set()
  var buffer: String = ""
  val delimiter: String = "~"


  IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", 8000))

  override def receive: Receive = {
    case b:Bound => println("waiting on port: "+ b.localAddress.getPort)
    case c: Connected =>
      println("Client Connected: " + c.remoteAddress)
      this.webServers = this.webServers + sender()
      sender() ! Register(self)
    case PeerClosed =>
      println("Client Disconnected: " + sender())
      this.webServers = this.webServers - sender()
    case r:Received =>
      buffer += r.data.utf8String
      while (buffer.contains(delimiter)){
        val curr = buffer.substring(0, buffer.indexOf(delimiter))
        buffer = buffer.substring(buffer.indexOf(delimiter) + 1)
        webServerMessageHandler(curr)
      }
    case SendGameState =>
      gameActor ! Update
    case gs:GameState =>
      this.webServers.foreach((client: ActorRef) => client ! Write(ByteString(gs.gameState + delimiter)))
  }
  def webServerMessageHandler(message:String): Unit ={
    val newMessage: JsValue = Json.parse(message)
    val username = (newMessage \ "username").as[String]
    val actionType = (newMessage \ "acton").as[String]
    val x = (newMessage \ "x").as[Double]
    val y = (newMessage \ "y").as[Double]

    actionType match  {
      case "connected" =>
        gameActor ! AddPlayer(username,x,y)
      case "disconnected" =>
        gameActor ! RemovePlayer(username)
      case "move"=> gameActor ! movePlayer(username,x,y)
    }
  }
}
object GameServer {
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem()

    import actorSystem.dispatcher

    import scala.concurrent.duration._

    val game = actorSystem.actorOf(Props(classOf[GameActor]))
    val server = actorSystem.actorOf(Props(classOf[GameServer],game))
    actorSystem.scheduler.schedule(16.milliseconds, 32.milliseconds, server, Update)
    actorSystem.scheduler.schedule(32.milliseconds, 32.milliseconds, server, SendGameState)
  }
}
