package main

import physics._
import objects._

class Player(var maxHealth: Int, var strength: Int, var location: PhysicsVector, var velocity: PhysicsVector,
             var orientation: PhysicsVector, var username: String, var health: Int) extends PhysicalObject(location, velocity) {

  //  var health: Int = maxHealth

  def takeDamage(projectile: Projectile): Unit = {
    if (projectile.location == this.location) {
      this.health -= projectile.damage
      if (this.health < 0) {
        this.health = 0
      }
    }
  }

  def get_orientation(): Unit = {
    if (velocity.x < 0 && velocity.y == 0) {
      orientation.x = -1.0
      orientation.y = 0.0
      orientation.z = 0.0
    }
    if (velocity.x > 0 && velocity.y == 0) {
      orientation.x = 1.0
      orientation.y = 0.0
      orientation.z = 0.0
    }
    if (velocity.x == 0 && velocity.y < 0) {
      orientation.x = 0.0
      orientation.y = -1.0
      orientation.z = 0.0
    }
    if (velocity.x == 0 && velocity.y > 0) {
      orientation.x = 0.0
      orientation.y = 1.0
      orientation.z = 0.0
    }

  }
}
