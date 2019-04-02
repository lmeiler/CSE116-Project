package main

class InAirStatic(player: Player) extends PlayerState(player) {

  override def jumpPress(): Unit = {
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
