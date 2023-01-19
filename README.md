# movie-fleur 🌻
[![emoji-log](https://cdn.rawgit.com/ahmadawais/stuff/ca97874/emoji-log/flat-round.svg)](https://github.com/ahmadawais/Emoji-Log/)
[![CircleCI](https://circleci.com/gh/Wetwer/movie-fleur/tree/master.svg?style=svg)](https://circleci.com/gh/Wetwer/movie-fleur/tree/master)

Convert 3D movies with splited Frames to Anaglyph 3D movies to watch with 3d Red (Blue/Cyan/Green) Glasses

### Convert from video file with given framePosition
If a 3D video is available the frames can be extracted from the Video by applying the frame Position. 3D video Files can be found here: <a href="https://yts.mx/browse-movies/0/3D/all/0/latest/0/all">YTS</a>

```java
BufferedImage frame = FleurVideo.extract(new File("3dVideo.mp4"), 6000);

List<BufferedImage> splited = Fleur3dUtil.split(frame);
BufferedImage frameCombined = FleurFilter.additive(
        FleurFilter.color(splited.get(0), ColorMask.RED),
        FleurFilter.color(splited.get(1), ColorMask.CYAN));

ImageHelper.saveImage(frameCombined, "frame_combined.png");
```

#### 1. Extract frames from video file
<img src="https://mask.imgur.com/ODNFPZ6.jpg" width="50%">

```java
FleurVideo.extract(new File("3dVideo.mp4"), 6000);
```


#### 2. Split and stretch frames
<table>
    <tr>
        <th>
            left
        </th>
        <th>
            right
        </th>
    </tr>
    <tr>
        <td>
            <img src="https://mask.imgur.com/S2jKdVA.jpg">
        </td>
        <td>
            <img src="https://mask.imgur.com/krLVxOF.jpg">
        </td>
    </tr>
</table>


```java
List<BufferedImage> splited = Fleur3dUtil.split(frame);
```


#### 3. Combine Frames to one image (No color filtering)

<img src="https://mask.imgur.com/lFv7n8y.jpg" width="50%">


```java
BufferedImage combined = FleurFilter.alphaCombine(frame1, frame2);
```


#### 4. Apply color filter

<table>
    <tr>
        <th>
            left
        </th>
        <th>
            right
        </th>
    </tr>
    <tr>
        <td>
            <img src="https://mask.imgur.com/YKJuLNS.png">
        </td>
        <td>
            <img src="https://i.imgur.com/hNGX1do.jpg">
        </td>
    </tr>
    <tr>
        <td>
            RGB(x, 0, 0) / 0xFFFF0000
        </td>
        <td>
            RGB(0, x, x) / 0xFF00FFFF
        </td>
    </tr>
</table>


```java
BufferedImage redFilterImg = FleurFilter.color(splited.get(0), FilterColor.RED);
BufferedImage greenBlueFilterImg = FleurFilter.color(splited.get(1), FilterColor.CYAN);
```


#### 5. Combined with filter

<img src="https://i.imgur.com/hgqPHa2.jpg" width="50%">

Additive Color filtering => For every Pixel on final Image RGB(LeftImage.red, RightImage.green, RightImage.blue)

```java
BufferedImage additiveCombinedFrame = FleurFilter.additive(
        FleurFilter.color(splited.get(0), FilterColor.RED),
        FleurFilter.color(splited.get(1), FilterColor.CYAN)
);
```

### 6. Compile to Video

<img src="https://github.com/Wetwer/movie-fleur/blob/master/demo/gif_default.gif?raw=true" width="50%">


```java
// Extract images from Video (specific from frames 6000 - 6200)
List<BufferedImage> images
        = FleurVideo.extract(new File("3dVideo.mp4"), 6000, 6010);

List<BufferedImage> videoImages = new ArrayList<>();
for (BufferedImage image : images) {
    List<BufferedImage> splits = Fleur3dUtil.split(image);

    videoImages.add(FleurFilter.additive(
            FleurFilter.color(splits.get(0), ColorMask.RED),
            FleurFilter.color(splits.get(1), ColorMask.CYAN)
    ));
}

FleurVideo.create(videoImages, "3dVideoOut.mp4");
```


### 7. Compile to polarized 3d Video (left/right)

<img src="https://github.com/Wetwer/movie-fleur/blob/master/demo/gif_left.gif?raw=true" width="50%">
<img src="https://github.com/Wetwer/movie-fleur/blob/master/demo/gif_right.gif?raw=true" width="50%">


```java
List<BufferedImage> images = FleurVideo.extract(new File("3dVideo.mp4"), 6000, 6100);

List<BufferedImage> leftArray = new ArrayList<>();
List<BufferedImage> rightArray = new ArrayList<>();

for (BufferedImage image : images) {
    List<BufferedImage> splits = Fleur3dUtil.split(image);
    leftArray.add(splits.get(0));
    rightArray.add(splits.get(1));
}

FleurVideo.create(leftArray, "left.mp4");
FleurVideo.create(rightArray, "right.mp4");
```


To use: play both videos at the same frames but on two diffrent beamers.... I dont have two beamers... well. I havent tested this  acctually
<br>
<img src="https://www.thorlabs.com/images/TabImages/3D_Cinema_A1-800.jpg">
<br>
Or use active shutter 3d glasses requires 60hz monitor and switching on each frame to the other video... 
<a href="https://en.wikipedia.org/wiki/Active_shutter_3D_system">Active_shutter_3D_system</a>
#### Further Filters

  <table>
        <tr>
            <th>Default</th>
            <td>
                <img src="https://i.imgur.com/ZcNK1s2.jpg" width="500px">
            </td>
            <td></td>
        </tr>
        <tr>
            <th>Additive Blue Yellow</th>
            <td>
                <img src="https://i.imgur.com/skpQs5x.jpg" width="500px">
            </td>
            <td>
               <pre>
FleurFilter.additive(
        FleurFilter.color(splits.get(0), ColorMask.BLUE),
        FleurFilter.color(splits.get(1), ColorMask.YELLOW)
);               </pre>
            </td>
        </tr>
        <tr>
            <th>Additive Green Magenta</th>
            <td>
                <img src="https://i.imgur.com/Hr3l2PD.jpg" width="500px">
            </td>
            <td>
               <pre>
FleurFilter.additive(
        FleurFilter.color(splits.get(0), ColorMask.GREEN),
        FleurFilter.color(splits.get(1), ColorMask.MAGENTA)
);               </pre>
            </td>
        </tr>
        <tr>
            <th>Indexed</th>
            <td>
                <img src="https://i.imgur.com/dBI2ZFI.png" width="500px">
            </td>
            <td>
               <pre>
ImageHelper.convertToType(frame, BufferedImage.TYPE_BYTE_INDEXED)
               </pre>
            </td>
        </tr>
        <tr>
            <th>Red</th>
            <td>
                <img src="https://mask.imgur.com/YKJuLNS.png" width="500px">
            </td>
            <td>
               <pre>
FleurFilter.color(frame, ColorMask.RED)
               </pre>
            </td>
        </tr>
        <tr>
            <th>Green</th>
            <td>
                <img src="https://i.imgur.com/P84E62j.png" width="500px">
            </td>
            <td>
               <pre>
FleurFilter.color(frame, ColorMask.GREEN)
               </pre>
            </td>
        </tr>
        <tr>
            <th>Blue</th>
            <td>
                <img src="https://i.imgur.com/lXIyDEm.jpg" width="500px">
            </td>
            <td>
               <pre>
FleurFilter.color(frame, ColorMask.BLUE)
               </pre>
            </td>
        </tr>
        <tr>
            <th>Cyan</th>
            <td>
                <img src="https://i.imgur.com/hNGX1do.jpg" width="500px">
            </td>
            <td>
               <pre>
FleurFilter.color(frame, ColorMask.CYAN)
               </pre>
            </td>
        </tr>
        <tr>
            <th>Gray</th>
            <td>
                <img src="https://i.imgur.com/C6A2pMO.jpg" width="500px">
            </td>
            <td>
               <pre>
FleurFilter.gray(frame)
               </pre>
            </td>
        </tr>
        <tr>
            <th>Binary</th>
            <td>
                <img src="https://i.imgur.com/FyijEmt.png" width="500px">
            </td>
            <td>
               <pre>
ImageHelper.convertToType(frame, BufferedImage.TYPE_BYTE_BINARY)
               </pre>
            </td>
        </tr>
        <tr>
            <th>Transparent (0.5)</th>
            <td>
                <img src="https://i.imgur.com/zMNBL6G.jpg" width="500px">
            </td>
            <td>
               <pre>
FleurFilter.transparent(frame, 0.5)
               </pre>
            </td>
        </tr>
        <tr>
            <th>Invert</th>
            <td>
                <img src="https://i.imgur.com/RXWopYs.jpg" width="500px">
            </td>
            <td>
               <pre>
FleurFilter.invert(frame)
               </pre>
            </td>
        </tr>
    </table>
