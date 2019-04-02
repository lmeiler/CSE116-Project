package main

class InAirRight(player: Player) extends PlayerState(player) {

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
