package physics

import java.lang.Math.{max, min}

abstract class PhysicalObject(var location: PhysicsVector, var velocity: PhysicsVector) {

  def computePotentialLocation(deltaTime: Double): PhysicsVector = {
    var velocityVector: PhysicsVector = this.velocity
    var locationVector: PhysicsVector = this.location
    var velocityX: Double = velocityVector.x
    var velocityY: Double = velocityVector.y
//    var velocityZ: Double = velocityVector.z
    var locationX: Double = locationVector.x
    var locationY: Double = locationVector.y
//    var locationZ: Double = locationVector.z
    var xDistanceTraveled: Double = velocityX * deltaTime
    var yDistanceTraveled: Double = velocityY * deltaTime
//    var zDistanceTraveled: Double = velocityZ * deltaTime
    var newXLocation: Double = locationX + xDistanceTraveled
    var newYLocation: Double = locationY + yDistanceTraveled
//    var newZLocation: Double = (locationZ + zDistanceTraveled)
    var newVector: PhysicsVector = new PhysicsVector(newXLocation, newYLocation)
    return newVector
  }

  def updateVelocity(world: World, deltaTime: Double): Unit = {
    var objectVelocity: PhysicsVector = this.velocity
    var objectYVelocity: Double = objectVelocity.y
    var gravity: Double = world.gravity
    var deltaVelocity: Double = gravity*deltaTime
    var finalVelocity: Double = (objectYVelocity - deltaVelocity)
    //    var halfATSquared: Double = (1/2)*(gravity)*(deltaTime)*(deltaTime)
    var vNotT: Double = objectYVelocity*deltaTime
    //    var newZ: Double = vNotT - halfATSquared
    var newY: Double = this.location.y + vNotT
    var newVelocity: PhysicsVector = new PhysicsVector(this.velocity.x, finalVelocity)
    var newNonYVelocity: PhysicsVector = new PhysicsVector(this.velocity.x, 0.0)
    this.velocity = newVelocity
  }

  def detectCollision(location3: PhysicsVector, boundaries: List[Boundary]): Boolean = {
    for (boundary <- boundaries) {
      var boundEnd1: PhysicsVector = boundary.end1
      var boundEnd2: PhysicsVector = boundary.end2
      var boundEnd1X: Double = boundEnd1.x
      var boundEnd1Y: Double = boundEnd1.y
      var boundEnd2X: Double = boundEnd2.x
      var boundEnd2Y: Double = boundEnd2.y
      var objx: Double = this.location.x
      var objy: Double = this.location.y
      var locx: Double = location3.x
      var locy: Double = location3.y
      var objectSlope: Double = (locy - objy) / (locx - objx)
      var boundSlope: Double = (boundEnd2Y - boundEnd1Y) / (boundEnd2X - boundEnd1X)
      var objYInt: Double = (objy - (objectSlope) * objx)
      var boundYInt: Double = (boundEnd1Y - (boundSlope) * boundEnd1X)
      var xIntercept: Double = (boundYInt - objYInt) / (objectSlope - boundSlope)
      var minX1: Double = min(objx, locx)
      var minX2: Double = min(boundEnd1X, boundEnd2X)
      var maxX1: Double = max(objx, locx)
      var maxX2: Double = max(boundEnd1X, boundEnd2X)
      if (minX1 <= xIntercept && xIntercept <= maxX1) {
        if (minX2 <= xIntercept && xIntercept <= maxX2) {
          return false
        }
      }
    }
    return true
  }

    //        }
//
//      }
//      else {
//        return true
//      }
//    }
//    else {
//      return true
//    }
//  }

//  def updateWorld(world: World, deltaTime: Double): Unit = {
//    for (obj <- world.objects) {
//      updateVelocity(obj, world, deltaTime)
//      var collision: Boolean = false
//      for (bound <- world.boundaries) {
//        if (!detectCollision(obj, computePotentialLocation(obj, deltaTime), bound)) {
//          collision = true
//        }
//      }
//      if (collision == true) {
//        obj.location.z = computePotentialLocation(obj, deltaTime).z
//      }
//      else {
//        obj.location = computePotentialLocation(obj, deltaTime)
//      }
//    }
//  }
}
