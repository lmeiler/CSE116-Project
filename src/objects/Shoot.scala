package objects

import physics.PhysicalObject
import physics.PhysicsVector

class Shoot(location: PhysicsVector, velocity: PhysicsVector, val mass: Double)
  extends PhysicalObject (location, velocity){
/*
  def shooting(orienation: PhysicsVector, strength: Int): Unit = {
    this.velocity.x = orienation.x * strength
    this.velocity.y = orienation.y * strength
//    this.velocity.z = strength
  }
  */

}
