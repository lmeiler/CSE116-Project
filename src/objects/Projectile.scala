package objects

import physics.PhysicsVector
import main._

class Projectile(var location: PhysicsVector, var velocity: PhysicsVector)
  extends InanimateObject(location, velocity) {

  var damage: Int = 2

  override def use(player: Player): Unit = {
    this.velocity.x = player.orientation.x*player.strength
    this.velocity.y = player.orientation.y*player.strength
    //this.velocity.z = player.orientation.z*player.strength
  }
}
