package main

import physics.PhysicsVector

class InAirStatic(player: Player) extends PlayerState(player) {

  player.orientation = new PhysicsVector(1,0)

  override def jumpPress(): Unit = {
    player.velocity.y = -180
  }

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
    player.moveRight()
    if (player.velocity.y == 0) {
      player.state = new WalkingRight(player)
    }
    if (player.velocity.y < 0 || player.velocity.y > 0) {
      player.state = new InAirRight(player)
    }
  }
}
