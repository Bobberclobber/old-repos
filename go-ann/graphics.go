package main

import (
	"fmt"
	"image"
	"image/color"
	"strconv"

	"github.com/llgcode/draw2d"
	"github.com/llgcode/draw2d/draw2dimg"
	"github.com/llgcode/draw2d/draw2dkit"
)

const startx = 100
const starty = 100

// A Pos contains the x- and y-coordinate for a neuron in an image
type Pos struct {
	x, y float64
}

func drawNetwork(levels []*Level) {
	// Initialize the graphic context on an RGBA image
	dest := image.NewRGBA(image.Rect(0, 0, 3000, 2000))
	gc := draw2dimg.NewGraphicContext(dest)

	// TODO: Add support for levels with differing number of neurons
	// Calculate x- and y-steps based on the number of neurons and levels
	stepx := 3000 / len(levels[0].neurons)
	stepy := 2000 / len(levels)

	// Map linking neuron ID to image position
	posMap := make(map[int]Pos)

	// Build the pos map
	for i := 0; i < len(levels); i++ {
		for j := 0; j < len(levels[i].neurons); j++ {
			posMap[levels[i].neurons[j].id] = Pos{float64(startx + j*stepx), float64(starty + i*stepy)}
		}
	}

	// Draw the network
	for i := 0; i < len(levels); i++ {
		for j := 0; j < len(levels[i].neurons); j++ {
			x := float64(startx + j*stepx)
			y := float64(starty + i*stepy)
			n := levels[i].neurons[j]
			// Draw lines to all children
			gc.SetStrokeColor(color.RGBA{0, 0, 0, 255})
			gc.SetLineWidth(2)
			for k := 0; k < len(n.children); k++ {
				c := n.children[k].dest
				gc.MoveTo(x, y)
				gc.LineTo(posMap[c.id].x, posMap[c.id].y)
				gc.Stroke()
			}
			drawCircle(gc, x, y, 50, 255, 0, 0)
			drawText(gc, x-18, y+12, strconv.Itoa(n.id))
		}
	}

	// Save to file
	draw2dimg.SaveToPngFile("network.png", dest)

}

func drawCircle(gc *draw2dimg.GraphicContext, x, y, r float64, R, G, B uint8) {
	if R > 255 || G > 255 || B > 255 || R < 0 || G < 0 || B < 0 {
		panic(fmt.Sprintf("Invalid RGB"))
	}
	// Set some properties
	gc.SetFillColor(color.RGBA{R, G, B, 255})
	gc.SetStrokeColor(color.RGBA{0, 0, 0, 255})
	gc.SetLineWidth(1)

	draw2dkit.Circle(gc, x, y, r)
	gc.FillStroke()
}

func drawText(gc *draw2dimg.GraphicContext, x, y float64, text string) {
	// Set the global folder for searching fonts
	draw2d.SetFontFolder("resource/font")
	// Set the font luximb.ttf
	gc.SetFontData(draw2d.FontData{Name: "luxi", Family: draw2d.FontFamilyMono, Style: draw2d.FontStyleBold})
	// Set the fill text color to black
	gc.SetFillColor(image.Black)
	gc.SetFontSize(30)
	// Display text
	gc.FillStringAt(text, x, y)
}
