package objects

import physics.{PhysicalObject, PhysicsVector}
import main.Player

abstract class InanimateObject(location: PhysicsVector, velocity: PhysicsVector) extends PhysicalObject(location, velocity) {

  def use(player: Player): Unit
}
