import struct
import gzip
from graphics import *


IMAGE_FILE = '../../data/t10k-images-idx3-ubyte.gz'
LABEL_FILE = '../../data/t10k-labels-idx1-ubyte.gz'


def read_meta_data():
    """
    Read number of images and number of rows
    and columns per image from the image file
    """
    images = 0
    rows = 0
    cols = 0
    with gzip.open(IMAGE_FILE, 'rb') as imgf:
        # Skip the 32-bit magic number
        imgf.read(4)
        # Read the number of images
        images = struct.unpack('>L', imgf.read(4))[0]
        # Read number of rows and columns per image
        rows = struct.unpack('>L', imgf.read(4))[0]
        cols = struct.unpack('>L', imgf.read(4))[0]
    return images, rows, cols


def read_labels(num):
    labels = []
    with gzip.open(LABEL_FILE, 'rb') as lblf:
        # Skip the meta data
        lblf.read(8)
        # Read labels until all or enough has been read
        byte = lblf.read(1)
        count = 0
        while count < num:
            if not byte:
                break
            labels.append(struct.unpack('>B', byte)[0])
            byte = lblf.read(1)
            count += 1
    return labels


def read_image(index, img_len):
    """
    Read the image with specified index
    """
    image = []
    with gzip.open(IMAGE_FILE, 'rb') as imgf:
        # Skip meta data
        imgf.read(16)
        # Skip all bytes up to the image
        imgf.read(index*img_len)
        for i in range(0, img_len):
            image.append(struct.unpack('>B', imgf.read(1))[0])
    return image


def disp_image(window, image, rows, cols):
    """
    Display an array of pixels as a gray scale image
    """
    for ix in range(0, rows*cols):
        r = int(ix / rows)
        c = int(ix % rows)
        pt1 = Point(100+5*c, 100+5*r)
        pt2 = Point(100+5*c+4, 100+5*r+4)
        rect = Rectangle(pt1, pt2)
        rect.draw(window)
        val = 255 - image[ix]
        color = color_rgb(val, val, val)
        rect.setFill(color)
        rect.setOutline(color)


def main():
    win = GraphWin('MNIST Display', 500, 250)
    images, rows, cols = read_meta_data()
    while True:
        index = input('Choose image index: ')
        if index == 'q':
            win.close()
            break
        elif index < images and index >= 0:
            disp_image(win, read_image(int(index), rows*cols), rows, cols)
        else:
            print('Invalid index')
        #win.getMouse()
        #win.close()


if __name__ == '__main__':
    print("Running")
    main()
