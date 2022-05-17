package ref; /**
 *
 */


import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene scene = new Scene("Test scene");

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(150, 150).setVPDistance(1000);

		scene.geometries.add( //
				new Sphere(50d, new Point(0, 0, -50)).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
				new Sphere(25d, new Point(0, 0, -50)).setEmission(new Color(RED)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
		scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setKL(0.0004).setKQ(0.0000006));

		camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		scene.geometries.add( //
				new Sphere(400d, new Point(-950, -900, -1000)).setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.5)),
				new Sphere(200d, new Point(-950, -900, -1000)).setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKr(1)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
						new Point(-1500, -1500, -2000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKr(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKL(0.00001).setKQ(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200).setVPDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

		scene.geometries.add( //
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
				new Sphere(30d, new Point(60, 50, -50)).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
				.setKL(4E-5).setKQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();
	}
	/**
	 * Produce a picture of a pyramid lighted by a spot light with a mirror effects
	 */
	@Test
	public void pyramidTransparentSphere() {
		Camera camera = new Camera(new Point(-140, 20, 35), new Vector(1, -0.15, -0.25), new Vector(1, 0, 4))
				.setVPSize(200, 200).setVPDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));

		scene.geometries.add( //
				new Plane(new Point(10, 0, -30), new Point(0, 10, -30), new Point(-10, 0, -30))
						.setEmission(new Color(190,0,190)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0).setKr(1)),
				new Polygon(new Point(10, 0, -10), new Point(0, 10, -10), new Point(-10, 0, -10),
						new Point(0, -10, -10))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.2).setKr(0)),
				new Polygon(new Point(10, 0, -10), new Point(0, -10, -10), new Point(0, -10, 0),
						new Point(10, 0, 0))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0).setKr(1)),
				new Polygon(new Point(10, 0, -10), new Point(0, 10, -10), new Point(0, 10, 0),
						new Point(10, 0, 0))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.4)),
				new Polygon(new Point(-10, 0, -10), new Point(0, 10, -10), new Point(0, 10, 0),
						new Point(-10, 0, 0))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.8)),
				new Polygon(new Point(-10, 0, -10), new Point(0, -10, -10), new Point(0, -10, 0),
						new Point(-10, 0, 0))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.4)),
				new Polygon(new Point(10, 0, 0), new Point(0, -10, 0), new Point(-10, 0, 0),
						new Point(0, 10, 0))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.5)),
				new Triangle(new Point(10, 0, 0), new Point(0, -10, 0), new Point(0, 0, 10)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(60).setKt(0.5).setKr(0)), //
				new Triangle(new Point(10, 0, 0), new Point(0, 10, 0), new Point(0, 0, 10)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(60).setKt(1)), //
				new Triangle(new Point(-10, 0, 0), new Point(0, 10, 0), new Point(0, 0, 10)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(60).setKt(0.8)), //
				new Triangle(new Point(-10, 0, 0), new Point(0, -10, 0), new Point(0, 0, 10)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(60).setKt(1)), //
				new Sphere( 2,new Point(0, 0, 3)) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
				new Sphere(2,new Point(3, 0, -6)) //
						.setEmission(new Color(java.awt.Color.RED)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0).setKr(1)),
				new Sphere(2,new Point(-3, 3, -6) ) //
						.setEmission(new Color(java.awt.Color.GREEN)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.5)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(30, 30, 100), new Vector(0, 0, -1)) //
				.setKL(4E-5).setKQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadowPyramid", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
		camera.writeToImage();
	}
}


