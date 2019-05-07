package controller

import akka.actor.{Actor, ActorRef}
import main.Player


class PlayerActor(gameActor: GameActor, player:Player)  extends Actor{
  import context.dispatcher
  import scala.concurrent.duration

  var gameState = ""
  override def receive: Receive ={
    case Shoot=>
      if (gameState != "")
      player.shoot()

  }


}
