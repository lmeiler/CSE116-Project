package controller

import akka.actor.{Actor, ActorRef, PoisonPill, Props}
import main.{Database, Player}
import physics.{Boundary, PhysicsVector, World}

class GameActor(userName:String) extends Actor {
  val location = new PhysicsVector(0,0)
  val velocity = new PhysicsVector(0,0)
  val boundaries:List[Boundary] = List()
  val game = new World(boundaries)
  var player = new Player(location, velocity , userName, game)

  override def receive: Receive = {
    case message:AddPlayer =>
      if(Database.playerExists(message.username)){
        player = Database.loadPlayer(message.username,player)
      }
      else{
        Database.createPlayer(message.username,new PhysicsVector(message.x,message.y) )
      }
    case message:RemovePlayer => Database.removePlayer(message.username)

    case message:KillPlayer => Database.removePlayer(message.username)
      Database.createPlayer(message.username,new PhysicsVector(message.x,message.y))
    case message:movePlayer => player.location = new PhysicsVector(message.x,message.y)

  }
}
