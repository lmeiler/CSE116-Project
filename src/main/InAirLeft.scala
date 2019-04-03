package main

import physics.PhysicsVector

class InAirLeft(player: Player) extends PlayerState(player) {

  player.orientation = new PhysicsVector(-1,0)

  override def leftPress(): Unit = {
  }

  override def rightPress(): Unit = {
    player.moveRight()
    if (player.velocity.y == 0) {
      player.state = new WalkingRight(player)
    }
    if (player.velocity.y < 0 || player.velocity.y > 0) {
      player.state = new InAirRight(player)
    }
  }
}
