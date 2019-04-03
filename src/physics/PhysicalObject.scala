package physics

import java.lang.Math.{max, min}

abstract class PhysicalObject(var location: PhysicsVector, var velocity: PhysicsVector) {

  def computePotentialLocation(deltaTime: Double): PhysicsVector = {
    val xTraveled = this.location.x + this.velocity.x*deltaTime
    val yTraveled = this.location.y + this.velocity.y*deltaTime
    val newVector: PhysicsVector = new PhysicsVector(this.location.x + xTraveled, this.location.y + yTraveled)
    return newVector
  }

  def updateVelocity(world: World, deltaTime: Double): Unit = {
    val newVelocity: PhysicsVector = new PhysicsVector(this.velocity.x, this.velocity.y - world.gravity*deltaTime)
    this.velocity.y = newVelocity.y
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
