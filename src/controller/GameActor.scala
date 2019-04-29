package controller

import akka.actor.{Actor, ActorRef, PoisonPill, Props}
import physics.{Boundary, PhysicsVector, World}

class GameActor(list:List[Boundary]) extends Actor {

  var game = new World(list)
  override def receive: Receive = {
    case message:AddPlayer => game.addPlayer(message.username)
    case
  }
}
