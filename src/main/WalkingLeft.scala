package main

import physics.PhysicsVector

class WalkingLeft(player: Player) extends PlayerState(player) {

  player.orientation = new PhysicsVector(-1,0)

  override def leftPress(): Unit = {
  }

  override def rightPress(): Unit = {
    player.moveRight()
    player.state = new WalkingRight(player)
  }

  override def jumpPress(): Unit = {
    player.velocity.y = -180
    player.state = new InAirLeft(player)
  }
}
