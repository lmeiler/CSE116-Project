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
    player.velocity.y = -180
  }

  def jumpRelease(): Unit = {
    player.velocity.y = 0
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
    if (player.velocity.y == 0) {
      player.state = new Base(player)
    }
    else {
      player.state = new InAirStatic(player)
    }
  }

  def shoot(): Unit = {
    player.createProjectile()
  }
}
