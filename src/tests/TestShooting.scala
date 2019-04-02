//package tests
//import main._
//import objects.{Projectile, Shoot}
//import physics._
//import org.scalatest._
//
//class TestShooting extends FunSuite {
//  test("Test shoot method"){
//    val defaultHealth: Int = 20
//    val defaultStrength: Int = 5
//    val Location: PhysicsVector = new PhysicsVector(5.0, 0.0)
//    val Velocity: PhysicsVector = new PhysicsVector(3.0, 0.0)
//    val Orientation: PhysicsVector = new PhysicsVector(1.0, 0.0)
//
//    val player1 = new Player(defaultHealth, defaultStrength, Location, Velocity, Orientation, "Player", 15)
//    player1.shoot = new Projectile(new PhysicsVector(0.0, 0.0), new PhysicsVector(0.0, 0.0))
//    player1.shooting()
//
//    assert(player1.shoot.velocity.x == 2.0)
//    assert(player1.shoot.location.x == 5.1)
//  }
//
//}
