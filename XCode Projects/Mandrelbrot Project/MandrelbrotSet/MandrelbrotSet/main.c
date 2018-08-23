
#include <GLUT/glut.h>
#include <math.h>

void init(void);
void display(void);
void mandelbrot(void);

int main(int argc, char** argv)
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(800, 600);
    glutInitWindowPosition(0, 0);
    glutCreateWindow("Mandelbrot Set");
    init();
    glutDisplayFunc(display);
    glutMainLoop();
    return 0;
}

void init(void)
{
    glMatrixMode(GL_PROJECTION);
    gluOrtho2D(0.0, 800.0, 0.0, 600.0);
}

void display(void)
{
    glClearColor(1.0, 1.0, 1.0, 0.0);
    glClear(GL_COLOR_BUFFER_BIT);
    glColor3f(0.0, 0.0, 0.0);
    glPointSize(1.0);
    mandelbrot();
    glFlush();
}

void mandelbrot(void)
{
    double MinRealNum = -2.0;
    double MaxRealNum = 1.0;
    double MinImNum = -1.2;
    double ImageHeight = 600;
    double ImageWidth = 800;
    double MaxImNum = MinImNum+(MaxRealNum-MinRealNum)*ImageHeight/ImageWidth;
    double RealNum_factor = (MaxRealNum-MinRealNum)/(ImageWidth-1);
    double ImNum_factor = (MaxImNum-MinImNum)/(ImageHeight-1);
    unsigned MaxIterations = 30;
    double color;
    glBegin(GL_POINTS);
    
    for(unsigned y=0; y<ImageHeight; ++y)
    {
        double c_imNum = MaxImNum - y*ImNum_factor;
        
        for(unsigned x=0; x<ImageWidth; ++x)
        {
            double c_RealNum = MinRealNum + x*RealNum_factor;
            double Z_RealNum = c_RealNum, Z_ImNum = c_imNum;
            int isInside = 1;
            color = 1;
            
            for(unsigned n=0; n < MaxIterations; ++n)
            {
                
                double Z_RealNum2 = Z_RealNum*Z_RealNum, Z_ImNum2 = Z_ImNum*Z_ImNum;
                
                if(Z_RealNum2 + Z_ImNum2 > 4)
                {
                    
                    isInside = 0;
                    
                    for(unsigned q = 0; q < n; q++)
                    {
                        color = color - 0.05;
                    }
                    
                    break;
                }
                
                Z_ImNum = 2*Z_RealNum*Z_ImNum + c_imNum;
                Z_RealNum = Z_RealNum2 - Z_ImNum2 + c_RealNum;
                
            }
            
            if(isInside == 1)
            {
                glColor3f(0.0, 0.0, 0.0);
                glVertex2i(x, y);
            }
            
            else
            {
                glColor3f(color, color, color);
                glVertex2i(x, y);
            }
        }
    }
    glEnd();
}
