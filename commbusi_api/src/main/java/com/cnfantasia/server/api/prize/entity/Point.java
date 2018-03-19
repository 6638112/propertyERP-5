package com.cnfantasia.server.api.prize.entity;

public class Point {
	public double x;
	public double y;
	public Point(double x,double y){
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public static double getYByX(Point p1,Point p2,double x){
		double right = (p1.y-p2.y)*(p1.x-x)/(p1.x-p2.x);
		return p1.y-right;
	}
}
