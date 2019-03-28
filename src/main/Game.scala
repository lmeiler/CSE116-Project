package main

import physics.PhysicsVector

abstract class Game {

  val speed: Double = 5.0

  def playerMove()

  def jump()

  def shoot()


}
