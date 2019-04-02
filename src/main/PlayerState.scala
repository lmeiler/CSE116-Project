package main

abstract class PlayerState(player: Player) {

  def update(deltaTime: Double): Unit = {
    if (player.leftPress) {
      this.leftPress()
    }
    if (player.rightPress) {
      this.rightPress()
    }
  }

  def leftPress(): Unit = {
  }

  def rightPress(): Unit = {
  }

  def jumpPress(): Unit = {
  }

  def leftRelease(): Unit = {
    player.stop()
    if (player.velocity.y == 0) {
      player.state = new Base(player)
    }
    else {
      player.state = new InAirStatic(player)
    }
  }

  def rightRelease(): Unit = {
    player.stop()
    player.state = new Base(player)
  }

  def shoot(): Unit = {
    player.createProjectile()
  }
}
