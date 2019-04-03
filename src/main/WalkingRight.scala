package main

import physics.PhysicsVector

class WalkingRight(player: Player) extends PlayerState(player) {

  player.orientation = new PhysicsVector(1,0)

  override def leftPress(): Unit = {
    player.moveLeft()
    player.state = new WalkingLeft(player)
  }

  override def rightPress(): Unit = {
  }

  override def jumpPress(): Unit = {
    player.velocity.y = -180
    player.state = new InAirRight(player)
  }
}
