package objects

import physics.PhysicsVector
import physics._
import main._

class HealthPack(location: PhysicsVector, velocity: PhysicsVector, size: Int) extends InanimateObject(location, velocity) {

  var healing: Int = 5

  override def use(player: Player): Unit = {
    player.health = player.health + this.healing
  }
}
