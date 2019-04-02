package main

class Base(player: Player) extends PlayerState(player) {

  override def leftPress(): Unit = {
    player.moveLeft()
    player.state = new WalkingLeft(player)
  }

  override def rightPress(): Unit = {
    player.moveRight()
    player.state = new WalkingRight(player)
  }

  override def jumpPress(): Unit = {
    player.velocity.y = 15
  }
}
