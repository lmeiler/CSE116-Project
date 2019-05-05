package controller
import akka.actor.{Actor, ActorRef}
import main.Player


class PlayerActor(gameActor: GameActor, player:Player)  extends Actor{
  import context.dispatcher
  import scala.concurrent.duration

  override def receive: Receive ={
    case Shoot=> player.shoot()
  }


}
