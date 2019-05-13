# movie-fleur 🌻

Convert 3D movies with splited Frames to Anaglyph 3D movies to watch with 3d Red (Blue/Cyan/Green) Glasses

### Convert from single frame img (sample delivered in repo)
In this case an already extracted frame from a movie will be used to create the images. A sample can be found under img/frame_default.png
```java
VideoConverter videoConverterFromImg = new VideoConverter("img");
videoConverterFromImg.convertFromImg(new File("img/frame_default.png"));
```

### Convert from video file with given framePosition
If a 3D video is available the frames can be extracted from the Video by applying the frame Position. 3D video Files can be found here: <a href="https://yts.am/browse-movies/0/3D/all/0/downloads">YTS</a>
```java
//Build with VideoBuilder
VideoBuilder videoBuilder = VideoBuilder.builder().inputFile("3dVideo.mp4").build();
VideoConverter videoConverterFromVideo = new VideoConverter(videoBuilder, "img");
//Start converting
videoConverterFromVideo.convert(framePosition);
```


#### 1. Extract frames from video file
<img src="https://i.imgur.com/ODNFPZ6.jpg" width="50%">

#### 2. Split and stretch frames
<img src="https://i.imgur.com/S2jKdVA.jpg" width="49%">
<img src="https://i.imgur.com/krLVxOF.jpg" width="49%">
(left/right)

#### 3. Combine Frames to one image
<img src="https://i.imgur.com/lFv7n8y.jpg" width="50%">

#### (4.) Apply Grayscale to images
<img src="https://i.imgur.com/As5drlk.png" width="50%">

### TODO
    - 3D heightmap
    - grayscale -> color filter
    - commandline application
