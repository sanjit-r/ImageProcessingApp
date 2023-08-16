This application is made with model, view, controller design. It is able to put filters on images as well as rotate, flip, and create a histogram of the image.

Commands:

// load example.ppm and call it "example"
load res/example.ppm example

# brighten example by 50 and call it "example-brighter"
brighten 50 example example-brighter

# darken example by 50 and call it "example-darker"
darken 50 example example-darker

#save these
save res/example-brighter.ppm example-brighter
save res/example-darker.ppm example-darker

load res/website.jpg website
blur website website-blur
save res/website-blur.jpg website-blur



