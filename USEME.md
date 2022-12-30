# USEME

## Below are the script commands supported by this photoshop application, and examples for each one.

Note: Order matters when inputting in the arguments.

- If you want to edit an image...
    - You have to include the argument first
    - Followed by the image name
    - Ending with the name you want to call your changed image.

- For loading and saving images...
    - it starts with the argument name
    - followed by the image path (which is how you retrieve the picture from your laptop)
    - ends with the image name (what you want to call your image).

Special Case: If you want to brighten an image you will have to

- Put the argument first (brighten)
- Then followed by how much you want to brighten (or darken) by
- Brightening an image will be a positive whole number
- Darkening an image will be a negative whole number.
- Afterwards, include the image you want to brighten
- Finally, include the new image name that you want to assign to the brightened/darkened image.

Special Case: If you want to downsize an image you will have to

- Put the argument first (downsize)
- Then followed by how much you want to downsize the original height by
- Then followed by how much you want to downsize the original height by
- Afterwards, include the image you want to downsize
- Finally, include the new image name that you want to assign to the downsized image.

Loads an image into the photoshop program, from your computer.

- load imagePath imageName
    - ex: load image/grid.ppm grid

Saves an image from the Photoshop program to your computer.

- save imagePath imageName
    - ex: save image/savedGrid.ppm grid

Applies a greyscale filter using the red component of a RGB pixel.

- red-component image-name dest-image-name
    - ex: red-component grid grid-red

Applies a greyscale filter using the blue component of a RGB pixel.

- blue-component image-name dest-image-name
    - ex: blue-component grid grid-blue

Applies a greyscale filter using the green component of a RGB pixel.

- green-component image-name dest-image-name
    - ex: green-component grid grid-green

Applies a greyscale filter using the luma component of a RGB pixel.

- luma-component image-name dest-image-name
    - ex: luma-component grid grid-luma

Applies a greyscale filter using the value component of a RGB pixel.

- value-component image-name dest-image-name
    - ex: value-component grid grid-value

Applies a greyscale filter using the intensity component of a RGB pixel.

- intensity-component image-name dest-image-name
    - ex: intensity-component grid grid-intensity

Brightens an image by changing RGB values of a pixel.

- brighten num image-name dest-image-name
    - ex: brighten 10 grid grid-brighten

Flips an image horizontally.

- horizontal-flip image-name dest-image-name
    - ex: horizontal-flip grid grid-hFlip

Flips an image vertically.

- vertical-flip image-name dest-image-name
    - ex: vertical-flip grid grid-vFlip

Applies a blurring filter onto the image.

- blur image-name dest-image-name
    - ex: blur grid grid-blur

Applies a sharpening filter onto the image.

- sharpen image-name dest-image-name
    - ex: sharpen grid grid-sharpen

Applies a sepia filter onto the image.

- sepia image-name dest-image-name
    - ex: sepia grid grid-sepia

Applies a greyscale filter onto the image.

- greyscale image-name dest-image-name
    - ex: greyscale grid grid-greyscale

- downsize %ofOriginalHeight %ofOriginalWidth image-name dest-image-name
  - ex: downsize 90 90 grid grid-downsize


### How to use/test
- If you want to run the GUI
  - Run with no command line arguments
- If you want to run the script text file on the main method...
  run the main method
  - in the top right corner next to the build icon press "Photoshop"
  - Press edit configurations
  - And inside Program Arguments put in "-file res/script1.txt"
  - Now rerun the main method
- If you want to run regular line by line text on the main method...
  - in the top right corner next to the build icon press "Photoshop"
  - Press edit configurations
  - And inside Program Arguments put in "-text res/script1.txt"
  - Now rerun the main method
- If you want to run the script text file or line by line in a terminal/Command Prompt...
  - Direct yourself to the res folder of this program from the folder you're at currently.
  - Use "cd folderName" to access the next folder.
  - put in "java -jar restore.jar -file script.txt" for file.
  - put in "java -jar restore.jar -text" for text.
  - put in "java -jar restore.jar" for GUI.
  - It will then compile.