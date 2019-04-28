package controller
import akka.actor.{Actor, ActorRef}


class PlayerActor(gameActor: GameActor)  extends Actor{
  import context.dispatcher
  import scala.concurrent.duration

  override def receive: Receive ={
    case Shoot=>
  }


}
