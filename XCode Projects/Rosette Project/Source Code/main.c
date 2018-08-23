//
//  main.c
//  Rosette
//
//  Created by Matt on 2018-05-15.
//  Copyright © 2018 Matt's Apps. All rights reserved.
//


#include <GLUT/glut.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>


void ngon(int, float, float, float, float);
void init(void);
void display(void);
int vertex;


int main(int argc, char** argv)
{
    
    vertex = atoi(argv[1]); 
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(320, 320);
    glutInitWindowPosition(0, 0);
    glutCreateWindow("Ngon");
    init();
    glutDisplayFunc(display);
    glutMainLoop();
    return 0;
}

void ngon(int n, float cx, float cy, float radius,
          float rotAngle) {
    
    int cxArray[n];
    int cyArray[n];
    
    double angle, angleInc;
    int i;
    int j;
    
    if(n < 3)
        return;
    
    angle = rotAngle * 3.14159265 / 180;
    angleInc = 2 * 3.14159265 / n;
    
    
    //Do not use <= as it will mess up even numbers
    
    glBegin(GL_LINE_STRIP);
    for(i = 0; i < n; i++) {
        //creates a point
        //glVertex2d(radius * cos(angle) + cx, radius * sin(angle) + cy);
        //reassigns angle variable to be used when loops through again
        angle = angle + angleInc;
        
        cxArray[i] = (radius * cos(angle) + cx);
        cyArray[i] = (radius * sin(angle) + cy);
        
        //xarray[i] =radius * cos(angle) + cx
    }
    
    for(i = 0; i < n; i++)
    {
        for(j = 0; j < n; j++)
        {
           glVertex2d(cxArray[i],cyArray[i]);
           glVertex2d(cxArray[j],cyArray[j]);
        }
    }
    
    glEnd();
}

void init(void) {
    glMatrixMode(GL_PROJECTION);
    gluOrtho2D(0.0, 320.0, 0.0, 320.0);
}

void display(void) {
    glClearColor(0.0, 0.0, 0.0, 0.0);
    glClear(GL_COLOR_BUFFER_BIT);
    
    glEnable(GL_LINE_SMOOTH);
    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    
    glLineWidth(2.0);
   /* glColor3f(1.0, 1.0, 1.0);
    ngon(3, 160, 160, 16, 30);
    glColor3f(1.0, 0.0, 0.0);
    ngon(4, 160, 160, 32, 45);
    glColor3f(0.0, 1.0, 0.0);
    ngon(8, 160, 160, 100, 18);*/
    glColor3f(0.0, 0.0, 1.0);
    ngon(vertex, 160, 160, 128, 0);
    //ngon(256, 160, 160, 128, 0);
    
    glFlush();
}

