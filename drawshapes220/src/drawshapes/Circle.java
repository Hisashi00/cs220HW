package drawshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Circle extends AbstractShape
{
    private int diameter;
    
    public Circle(Color color, Point center, int diameter) {
        super(new Point(center.x, center.y));
        setBoundingBox(center.x - diameter/2, center.x + diameter/2, center.y - diameter/2, center.y + diameter/2);
        this.color = color;
        this.diameter = diameter;
        //System.out.println(this.anchorPoint);
    }

    @Override
    public void draw(Graphics g) {
        if (isSelected()){
            g.setColor(this.color.darker());
        } else {
            g.setColor(getColor());
        }
        g.fillOval((int)getAnchorPoint().getX() - diameter/2,
                (int)getAnchorPoint().getY() - diameter/2,
                diameter,
                diameter);
    }
    
    public String toString() {
        return String.format("CIRCLE %d %d %d %s %s", 
                this.getAnchorPoint().x, 
                this.getAnchorPoint().y,
                this.diameter,
                colorToString(this.getColor()),
                this.isSelected());
    }

    @Override
    public void setAnchorPoint(Point p) {
        this.anchorPoint = p;
        setBoundingBox(p.x - diameter / 2, p.x + diameter / 2,
                       p.y - diameter / 2, p.y + diameter / 2);
    }
    


    @Override
    public IShape copy(){
        return new Circle(this.color, new Point(anchorPoint.x, anchorPoint.y), this.diameter);
    }

    @Override
public void scaleUp() {
    diameter = (int)(diameter * 1.25);
    setBoundingBox(anchorPoint.x - diameter / 2, anchorPoint.x + diameter / 2,
                   anchorPoint.y - diameter / 2, anchorPoint.y + diameter / 2);
}

@Override
public void scaleDown() {
    diameter = (int)(diameter * 0.8);
    setBoundingBox(anchorPoint.x - diameter / 2, anchorPoint.x + diameter / 2,
                   anchorPoint.y - diameter / 2, anchorPoint.y + diameter / 2);
}


@Override
public double getArea() {
    double r = diameter / 2.0;
    return Math.PI * r * r;
}


    

}
