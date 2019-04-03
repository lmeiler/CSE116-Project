package main

import physics.PhysicsVector

class InAirRight(player: Player) extends PlayerState(player) {

  player.orientation = new PhysicsVector(1,0)

  override def leftPress(): Unit = {
    player.moveLeft()
    if (player.velocity.y == 0) {
      player.state = new WalkingLeft(player)
    }
    if (player.velocity.y < 0 || player.velocity.y > 0) {
      player.state = new InAirLeft(player)
    }
  }

  override def rightPress(): Unit = {
  }

}
