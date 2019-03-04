package main

import physics.PhysicsVector

class Game(player: Player){

  val speed: Double = 5.0

  def player_movement(direction: PhysicsVector): Unit = {
    player.velocity.x = direction.x * speed
    player.velocity.y = direction.y * speed
    player.get_orientation()

  }


}
