package main

class InAirLeft(player: Player) extends PlayerState(player) {

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
