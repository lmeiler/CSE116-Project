package main

import physics._
import objects._

class Player(var maxHealth: Int, var strength: Int, override var location: PhysicsVector, override var velocity: PhysicsVector,
             var orientation: PhysicsVector, var username: String, var health: Int) extends PhysicalObject(location, velocity) {

  //  var health: Int = maxHealth
  val speed: Int = 5
  val length: Int = 100
  val width: Int = 10
  var top: PhysicsVector = new PhysicsVector(this.location.y + 50, this.location.x)
  var bottom: PhysicsVector = new PhysicsVector(this.location.y - 50, this.location.x)

  def setTopBottom(): Unit = {
    this.top = new PhysicsVector(this.location.y + 50, this.location.x)
    this.bottom = new PhysicsVector(this.location.y - 50, this.location.x)
  }

  override def computePotentialLocation(deltaTime: Double): PhysicsVector = {
    var velocityVector: PhysicsVector = this.velocity
    var locationVector: PhysicsVector = this.location
    var velocityX: Double = velocityVector.x
    var velocityY: Double = velocityVector.y
    var locationX: Double = locationVector.x
    var locationY: Double = locationVector.y
    var xDistanceTraveled: Double = velocityX * deltaTime
    var yDistanceTraveled: Double = velocityY * deltaTime
    var newXLocation: Double = locationX + xDistanceTraveled
    var newYLocation: Double = locationY + yDistanceTraveled
    if (newYLocation <= 0) {
      var groundVector: PhysicsVector = new PhysicsVector(newXLocation, 0.0)
      return groundVector
    }
    else {
      var newVector: PhysicsVector = new PhysicsVector(newXLocation, newYLocation)
      return newVector
    }
  }

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
//      orientation.z = 0.0
    }
    if (velocity.x > 0 && velocity.y == 0) {
      orientation.x = 1.0
      orientation.y = 0.0
//      orientation.z = 0.0
    }
    if (velocity.x == 0 && velocity.y < 0) {
      orientation.x = 0.0
      orientation.y = -1.0
//      orientation.z = 0.0
    }
    if (velocity.x == 0 && velocity.y > 0) {
      orientation.x = 0.0
      orientation.y = 1.0
//      orientation.z = 0.0
    }
  }

  def player_movement(direction: PhysicsVector): Unit = {
    this.velocity.x = direction.x * speed
    this.velocity.y = direction.y * speed
    this.get_orientation()
  }

}
