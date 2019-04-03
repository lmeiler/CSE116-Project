package main

import physics._
import objects._

class Player(location: PhysicsVector, velocity: PhysicsVector, var username: String, val world: World) extends PhysicalObject(location, velocity) {

  var state: PlayerState = new Base(this)
  var leftPress = false
  var rightPress = false

  val orientation: PhysicsVector = new PhysicsVector(1,0)
  var health: Int = 10
  val speed: Int = 300
  val length: Int = 100
  val width: Int = 10

  var top: PhysicsVector = new PhysicsVector(this.location.x, this.location.y + 100)
  var bottom: PhysicsVector = new PhysicsVector(this.location.x, this.location.y)
  val playerBoundary: PlayerBoundary = new PlayerBoundary(bottom, top)


  // THESE ARE THE API METHODS
  def leftPressed(): Unit = {
    leftPress =  true
    this.state.leftPress()
  }

  def rightPressed(): Unit = {
    rightPress = true
    this.state.rightPress()
  }

  def jump(): Unit = {
    this.state.jumpPress()
  }

  def leftRelease(): Unit = {
    this.leftPress = false
    this.state.leftRelease()
  }

  def rightRelease(): Unit = {
    this.rightPress = false
    this.state.rightRelease()
  }

  def update(deltaTime: Double): Unit = {
    this.state.update(deltaTime)
  }

  def shoot(): Unit = {
    this.state.shoot()
  }

  //END API

  def moveLeft(): Unit = {
    this.velocity.x = -this.speed
  }

  def moveRight(): Unit = {
    this.velocity.x = this.speed
  }

//  def jump(): Unit = {
//    this.velocity.y = 15
//  }

  def stop(): Unit = {
    this.velocity.x = 0
  }

  def createProjectile(): Unit = {
    this.get_orientation()
    val projectileLocation: PhysicsVector = new PhysicsVector((this.location.x + (1 *this.orientation.x)) , this.location.y + 50)
    val projectileSpeed: PhysicsVector = new PhysicsVector(15 * this.orientation.x, 0)
    val projectile: Projectile = new Projectile(projectileLocation, projectileSpeed)
    this.world.projectiles += projectile
  }

  def setTopBottom(): Unit = {
    this.top = new PhysicsVector(this.location.y + 100, this.location.x)
    this.bottom = new PhysicsVector(this.location.y, this.location.x)
  }

  override def computePotentialLocation(deltaTime: Double): PhysicsVector = {
    val xDistanceTraveled = this.velocity.x*deltaTime
    val yDistanceTraveled = this.velocity.y*deltaTime
    new PhysicsVector(this.location.x + xDistanceTraveled, this.location.y + yDistanceTraveled)
  }

//  def takeDamage(projectile: Projectile): Unit = {
//    if (projectile.location == this.location) {
//      this.health -= projectile.damage
//      if (this.health < 0) {
//        this.health = 0
//      }
//    }
//  }

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

//  def player_movement(direction: PhysicsVector): Unit = {
//    this.velocity.x = direction.x * speed
//    this.velocity.y = direction.y * speed
//    this.get_orientation()
//  }

//  var shoot: Projectile = new Projectile(this.location, new PhysicsVector(0.0, 0.0))

//  def shooting(): Unit = {
//
//    get_orientation()
//    shoot.velocity.x = this.orientation.x * 2
//    shoot.velocity.y = 0
//    shoot.location.x = this.location.x + 0.1
////    shoot.location.y = this.location.y + 0.1
//  }

}
