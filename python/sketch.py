# -*- coding: utf-8 -*-
"""
Created on Sat Oct 28 20:51:39 2017

@author: Administrator
"""

from PIL import Image
import numpy as np

picName = '40.jpg'

picArr = np.asarray(Image.open(picName).convert('L')).astype('float')

depth=20
grad = np.gradient(picArr) #取横纵图像梯度值
grad_x, grad_y = grad
grad_x = grad_x*depth/100
grad_y = grad_y*depth/100
A = np.sqrt(grad_x**2 + grad_y**2 + 1.) #总体的灰度值
uni_x = grad_x/A
uni_y = grad_y/A
uni_z = 1./A #平面？

vec_el = np.pi/2.2                   # 光源的俯视角度，弧度值
vec_az = np.pi/4.                    # 光源的方位角度，弧度值

dx = np.cos(vec_el)*np.cos(vec_az)  #光源对x 轴的影响
dy = np.cos(vec_el)*np.sin(vec_az)   #光源对y 轴的影响
dz = np.sin(vec_el)              #光源对z 轴的影响

b = 255*(dx*uni_x + dy*uni_y + dz*uni_z)     #光源归一化
b = b.clip(0,255)
im = Image.fromarray(b.astype('uint8'))
im.save('./HandDrawing'+picName)



