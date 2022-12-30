# README

Photoshop is a program written in JAVA that allows the client to perform a variety of edits to an
image, specifically regarding brightening/darkening, flipping, and grey scaling an image. Clients
are able to upload any image of their choosing as long as it is in a ppm file format. Clients
are able to edit multiple images at once, just like in the GIMP image editing software. Afterwards,
they can save their image onto their device.

### How to use/test

- If you want to run the script text file on the main method...
    - run the main method
    - in the top right corner next to the build icon press "Photoshop"
    - Press edit configurations
    - And inside Program Arguments put in "res/script1.txt"
    - Now rerun the main method
- If you want torun the script text file in a terminal/Command Prompt...
    - Direct yourself to the res folder of this program from the folder you're at currently.
    - Use "cd folderName" to access the next folder.
    - Once at the res folder, put in "java -jar PhotoshopNew.jar script.txt"
    - It will then compile.
- You can also use the program normally and use line by line in the console within IntelliJ

## CONTROLLER INFO

- PhotoshopController is an interface that represents the controller for photoshop. This interface
  holds all the methods that are necessary in running the photoshop program, which in this case is
  runPhotoshop.
- PhotoshopControllerImpl is a class that represents the controller for photoshop. This class
  implements the methods in its related interface (runPhotoshop) which allows for the program to be
  run. It takes in the arguments supplied by the client (view), and runs the appropriate related
  method that would edit the image. Afterwards, it would take in the new image and assigned name,
  and then store it in a HashMap allowing the client to use it afterwards.
- RunPhotoshop is a method that runs the photoshop program. It takes in the 13 different potential
  edits that the client would possibly use, and runs the appropriate method. In each case, it'll
  output a success message in the console if the program is able to successfully edit the photo,
  and would output a failure message is the program cannot successfully edit the image.

### Updates made:

- We included 4 new potential edits that the client can make (9 -> 13)
- We also decided to create individual classes that would hold all the run methods that was
  originally in the Photoshop controller impl. class.
- This was to ensure that the switch statement looked cleaner
  and was easier to read. If we had to add more cases to the switch statement, it would also be
  easier to edit and read.
- We decided that adding in the new functionality to the already existing controller impl. was the
  best choice as it would consolidate all the functionality into one file, and the simplicity of the
  code justified editing it.
- We also moved the ImageUtil class to the controller as it was previously in the model, and
  it's the controller's job to take in inputs and outputs.

### Updates made 6/25: 

- We included 1 new edit the client can make (11 -> 12)
- We added a downsize method that would allow client to downsize an image.
- We set the original ControllerImpl to GUI and the TextFileControllerImpl to text and file scripting.
This was because the current controllerImpl is what the client would actually want to do, not text
or file based scripting. The TextFileControllerImpl was the previous controllerimpl

## INTERFACE INFO

#### Readers Note: Sharpen and Blur cannot take in large image files (ie Koala), as the runtime is too long

- PhotoshopInterface is an interface that represents the model for photoshop. This interface holds
  all the methods that are necessary for editing in this current version of photoshop, which are 13
  different methods to edit images. It also extends PhotoshopStateInterface.
- PhotoshopStateInterface is an interface that represents methods to view the current state of an
  image in photoshop, which are getPictureSize and getPixelColorAt.
- PhotoshopImpl is a class that represents the model for photoshop. This class implements the
  methods
  in both of the above interfaces listed. It can load and save images, and also edit images in 13
  different ways (six of them greyscaling, two of them flipping, one of them brightening, blurring,
  sharpening, greyscaling, and sepia filter). It also has the methods that would allow us to see the
  current state of an image, which would allow us to see if the images are properly edited.
- All 13 methods to edit, load and save are public as they need to be used by the client. We
  decided on making getPictureSize and getPixelColorAt as public as they can also be used by the
  client to check the images, and because the client is not capable of changing the image by using
  either of these methods.
- ImageUtil is a class that is used specifically for the load method. It reads through a ppm file,
  and puts the pixels in a 2D array to create the image.
- PixelClass is a class that is used to get the color of a pixel in RGB format. It only has getter
  methods that would retrieve the individual value that would represent one component of the three
  values in a RGB format. We decided on making this public so clients can see the individual
  components of the RGB format, and because the cannot edit the pixel color through these functions.
- MockPhotoshopModel is a mock model that is used for testing.
  Important functionalities in PhotoshopImpl:
- Load: Loads an image that the client can then edit.
- Save: Saves an image from the program to the clients computer.

### Updates made 6/17: 

- We included 4 more ways that the client can edit images (from 9 to 13)
- We moved the imageUtil class from the model to the controller (as explained above)
- For the 4 new ways the client can edit images, we put them in the additional operations impl.
  class which extends the original photoshop impl. and implements the methods in photoshop
  additional
  operations interface.
- The new photoshop additional operations interface holds all the new methods that we wanted to
  implement.
- We decided to create a new class and interface because it has different functionality from the
  rest of the previous methods, and adheres to open to extension, closed to modification.
- We included a MatrixUtil class to help with the new methods, as it was useful for matrices.
- We decided to edit the load and save method to handle different file formats because the
  functionality behind it is closely related to doing so for PPMs (takes in a file, outputs a file),
  so we decided to modify it.

### Updates madefor 6/25 Histogram and Extra Credit Pt1 (downsize):
- HistogramUtil was added to find frequencies of values in an image.
- We included the downsizing to the additionaloperationsimpl because it was easier to modify the
class than to change the model class that the controller had to take in to run the program. It was
less prone to error by doing this.


## VIEW INFO

- PhotoshopViewInterface is an interface that represents the view for photoshop. This interface
  holds
  all the methods necessary for a client to be able to view messages sent by the program, which is
  renderMessage.
- PhotoshopViewImpl is a class that represents the view for photoshop. The method in this class,
  renderMessage, allows for the client to see an image sent out by the program, whether it's a
  success message or a failure message. We set renderMessage as public as the program will have to
  send a message to the client.
- MockPhotoshopView is a mock model that is used for testing.

No updates were made to the view. (6/17)

### View updates made for 6/25
- We added a DrawHistogram Class that helps draws the histogram for the GUI.
- We added 4 classes to get the red, blue, green, and intensity (average) values for each individual 
histogram.
- We added a GUIImpl and a GUIInterface that would create and run the GUI. The GUIInterface holds
all the methods to run th GUI.
- The GUIImpl constructor creates the GUI.


## MAIN METHOD INFO

Photoshop class and its main method is where the client runs the photoshop program. Once the
console pops up, the client can then load images and start editing the photo(s). This method is
public as the client needs to use the main method to run the program.

### Updates made:

- We decided to edit the model to PhotoshopAdditionalOperationsImpl as it needs to take in the
  four new methods.
- We included an if statement to account for the client wanting to run from a text file instead from
  a keyboard.
- More updates made: We had to set the default no cli to GUI Impl instead of text
- We changed the model, view, controller needed to be taken in accordingly.

# Script of commands

Under the "src" directory in the photoshop class, run the main method.

Provide file "images/grid.ppm" in the command line argument by entering in "load images/grid.ppm"

Once you see a message reading "Successfully Loaded", you can enter one or more of the following
commands to greyscale, brighten, or flip an image. You can edit any number of images at the same
time, as long as they are in a separate command line every time. (You do not need to rerun the main
method every time you want to edit a new image). You can also apply multiple edits to the same
image, and am not restricted to one edit per running of the main method.

Below are the following script of commands that the program will accept

- load imagePath imageName
    - ex: load image/grid.ppm grid

- save imagePath imageName
    - ex: save image/savedGrid.ppm grid

- red-component image-name dest-image-name
    - ex: red-component grid grid-red

- blue-component image-name dest-image-name
    - ex: blue-component grid grid-blue

- green-component image-name dest-image-name
    - ex: green-component grid grid-green

- luma-component image-name dest-image-name
    - ex: luma-component grid grid-luma

- value-component image-name dest-image-name
    - ex: value-component grid grid-value

- intensity-component image-name dest-image-name
    - ex: intensity-component grid grid-intensity

- brighten num image-name dest-image-name
    - ex: brighten 10 grid grid-brighten

- horizontal-flip image-name dest-image-name
    - ex: horizontal-flip grid grid-hFlip

- vertical-flip image-name dest-image-name
    - ex: vertical-flip grid grid-vFlip

- blur image-name dest-image-name
    - ex: blur grid grid-blur

- sharpen image-name dest-image-name
    - ex: sharpen grid grid-sharpen

- sepia image-name dest-image-name
    - ex: sepia grid grid-sepia

- greyscale image-name dest-image-name
    - ex: greyscale grid grid-greyscale

- downsize %ofOriginalHeight %ofOriginalWidth image-name dest-image-name
  - ex: downsize 90 90 grid grid-downsize

# Citation

Grid and Grid.ppm image was created by Anthony Lim on 6/8/2022. Anthony Lim authorizes the usage
of Grid and Grid.ppm image for use in this project. "grid-blue.ppm", "grid-brighten.ppm",
"grid-darken.ppm", "grid-green.ppm", "green-hori-flipped.ppm", "grid-intensity.ppm",
"grid-luma.ppm", "grid-red.ppm", "grid-value.ppm", "grid-vert-flipped.ppm", was created by
Anthony Lim via GIMP. Anthony Lim authorizes the usage of the aforementioned images for use in
this project.

Flag and all related images was taken by Anthony Lim. Anthony Lim authorizes the usage of Flag and
all related images for use in this project. Flag and all it's related photos can be found under
res/PartialImageManipulationPhotos. Also in res/downsize.