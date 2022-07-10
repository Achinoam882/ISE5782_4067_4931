package renderer;

import lighting.DirectionalLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;

import lighting.AmbientLight;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;
import scene.SceneBuilder;

import static java.awt.Color.*;


public class image {


    //private Scene scene = new Scene("Test scene");


    @Test
    public void flag_imageTest1() {
        Scene scene = new Scene("Flag_scene");
        Point camera_position = new Point(500, 0, -1000);
        Point center_of_logo = new Point(0, 0, -200);
        Vector camera_vector = (center_of_logo.subtract(camera_position)).normalize();
        Camera camera=new Camera(camera_position,camera_vector, new Vector(0, -1, 0))
                .setVPSize(200, 200).setVPDistance(500);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));

        scene.geometries.add(
                new Polygon(new Point(-40, -22, 0), new Point(-40, 22, 0), new Point(40, 22, 0), new Point(40, -22, 0))
                        .setEmission(new Color(java.awt.Color.white))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Polygon(new Point(-40, 15, -1), new Point(-40, 20, -1), new Point(40, 20, -1), new Point(40, 15, -1))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Polygon(new Point(-40, -15, -1), new Point(-40, -20, -1), new Point(40, -20, -1), new Point(40, -15, -1))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(15, 6, -1), new Point(-15, 6, -1), new Point(0, -14.5, -1))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Polygon(new Point(-3, 4.5, -1.1), new Point(3, 4.5, -1.1), new Point(7, -1, -1.1), new Point(3, -6, -1.1), new Point(-3, -6, -1.1), new Point(-7, -1, -1.1))
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(-15, -8, -1), new Point(15, -8, -1), new Point(0, 12.5, -1))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(-2, 6.5, -1.2), new Point(2, 6.5, -1.2), new Point(0, 9.5, -1.2))
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(2, -8.5, -1.2), new Point(-2, -8.5, -1.2), new Point(0, -11.5, -1.2))
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(-10.5, 4, -1.2), new Point(-5.5, 4, -1.2), new Point(-8, 1, -1.2))
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(10.5, -6, -1.2), new Point(5.5, -6, -1.2), new Point(8, -3, -1.2))
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(10.5, 4, -1.2), new Point(5.5, 4, -1.2), new Point(8, 1, -1.2))
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(-10.5, -6, -1.2), new Point(-5.5, -6, -1.2), new Point(-8, -3, -1.2))
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),

                new Sphere( 96,new Point(30, 0, -100)).setEmission( Color.BLACK).setMaterial( new Material(new Double3(0.8), new Double3(0.8), 30, new Double3(0.8), new Double3(0.0))),

                new Sphere( 74,new Point(30, 0, -100)).setEmission( Color.BLACK).setMaterial( new Material(new Double3(0.8), new Double3(0.8), 30, new Double3(0.8), new Double3(0.0))),

                new Polygon(new Point(-250, 100, -50),new Point(250, 100, -50),  new Point(900, 100, -350), new Point(-900, 100, -350)).setEmission(Color.BLACK).setMaterial(new Material(new Double3(0.8), new Double3(1.0), 10000, new Double3(0.0), new Double3(1.0))));

        scene.lights.add(
                new DirectionalLight(new Color(10, 10, 10), new Vector(1, -1, 0)));
        scene.lights.add(
                new SpotLight(new Color(400, 400, 1020), new Point(-150, -150, -50), new Vector(2, 2, -3)));
        ImageWriter imageWriter = new ImageWriter("FlagBeforeSuperSampling", 500, 500);
        camera.setImageWriter(imageWriter) //
               // .setPixels(5,5)
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera.writeToImage();
    }
    @Test
    public void flag_imageTest2() {
        Scene scene = new Scene("Flag_scene");
        Point camera_position = new Point(500, 0, -1000);
        Point center_of_logo = new Point(0, 0, -200);
        Vector camera_vector = (center_of_logo.subtract(camera_position)).normalize();
        Camera camera=new Camera(camera_position,camera_vector, new Vector(0, -1, 0))
                .setVPSize(200, 200).setVPDistance(500);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));

        scene.geometries.add(
                new Polygon(new Point(-40, -22, 0), new Point(-40, 22, 0), new Point(40, 22, 0), new Point(40, -22, 0))
                        .setEmission(new Color(java.awt.Color.white))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Polygon(new Point(-40, 15, -1), new Point(-40, 20, -1), new Point(40, 20, -1), new Point(40, 15, -1))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Polygon(new Point(-40, -15, -1), new Point(-40, -20, -1), new Point(40, -20, -1), new Point(40, -15, -1))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(15, 6, -1), new Point(-15, 6, -1), new Point(0, -14.5, -1))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Polygon(new Point(-3, 4.5, -1.1), new Point(3, 4.5, -1.1), new Point(7, -1, -1.1), new Point(3, -6, -1.1), new Point(-3, -6, -1.1), new Point(-7, -1, -1.1))
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(-15, -8, -1), new Point(15, -8, -1), new Point(0, 12.5, -1))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(-2, 6.5, -1.2), new Point(2, 6.5, -1.2), new Point(0, 9.5, -1.2))
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(2, -8.5, -1.2), new Point(-2, -8.5, -1.2), new Point(0, -11.5, -1.2))
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(-10.5, 4, -1.2), new Point(-5.5, 4, -1.2), new Point(-8, 1, -1.2))
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(10.5, -6, -1.2), new Point(5.5, -6, -1.2), new Point(8, -3, -1.2))
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(10.5, 4, -1.2), new Point(5.5, 4, -1.2), new Point(8, 1, -1.2))
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(-10.5, -6, -1.2), new Point(-5.5, -6, -1.2), new Point(-8, -3, -1.2))
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),

                new Sphere( 96,new Point(30, 0, -100)).setEmission( Color.BLACK).setMaterial( new Material(new Double3(0.8), new Double3(0.8), 30, new Double3(0.8), new Double3(0.0))),

                new Sphere( 74,new Point(30, 0, -100)).setEmission( Color.BLACK).setMaterial( new Material(new Double3(0.8), new Double3(0.8), 30, new Double3(0.8), new Double3(0.0))),



                new Polygon(new Point(-250, 100, -50),new Point(250, 100, -50),  new Point(900, 100, -350), new Point(-900, 100, -350)).setEmission(Color.BLACK).setMaterial(new Material(new Double3(0.8), new Double3(1.0), 10000, new Double3(0.0), new Double3(1.0))));

        scene.lights.add(
                new DirectionalLight(new Color(10, 10, 10), new Vector(1, -1, 0)));
        scene.lights.add(
                new SpotLight(new Color(400, 400, 1020), new Point(-150, -150, -50), new Vector(2, 2, -3)));
        ImageWriter imageWriter = new ImageWriter("FlagAfterSuperSampling", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setPixels(5,5)
               //.setRayTracer(new RayTracerSuperSampling(scene, camera, 20))

               .setRayTracer(new RayTracerBasic(scene).turnAllBoxesOn())//
               // .renderImage(); //
                .renderImageWithTreads();
        camera.writeToImage();
    }


}