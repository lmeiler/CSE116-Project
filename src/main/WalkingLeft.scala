package main

class WalkingLeft(player: Player) extends PlayerState(player) {

  override def leftPress(): Unit = {
  }

  override def rightPress(): Unit = {
    player.moveRight()
    player.state = new WalkingRight(player)
  }

  override def jumpPress(): Unit = {
    player.velocity.y = -180
  }
}
