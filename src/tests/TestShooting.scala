package tests
import main._
import objects.Shoot
import physics._
import org.scalatest._

class TestShooting extends FunSuite {
  test("Test shoot method"){
    val defaultHealth: Int = 20
    val defaultStrength: Int = 5
    val Location: PhysicsVector = new PhysicsVector(5.0, 0.0)
    val Velocity: PhysicsVector = new PhysicsVector(3.0, 0.0)
    val Orientation: PhysicsVector = new PhysicsVector(1.0, 0.0)

    val player1 = new Player(defaultHealth, defaultStrength, Location, Velocity, Orientation, "Player", 15)
    player1.get_orientation()

    val shoot1: Shoot = new Shoot(player1.location, player1.velocity, 3)
    shoot1.shooting(player1.orientation, 3)
    assert(shoot1.velocity.x == 3.0)
    assert(shoot1.velocity.y == 0.0)
  }

}
