# movie-fleur 🌻
[![emoji-log](https://cdn.rawgit.com/ahmadawais/stuff/ca97874/emoji-log/flat-round.svg)](https://github.com/ahmadawais/Emoji-Log/)
[![CircleCI](https://circleci.com/gh/Wetwer/movie-fleur/tree/master.svg?style=svg)](https://circleci.com/gh/Wetwer/movie-fleur/tree/master)

Convert 3D movies with splited Frames to Anaglyph 3D movies to watch with 3d Red (Blue/Cyan/Green) Glasses

### Convert from video file with given framePosition
If a 3D video is available the frames can be extracted from the Video by applying the frame Position. 3D video Files can be found here: <a href="https://yts.am/browse-movies/0/3D/all/0/downloads">YTS</a>
```java
BufferedImage frame = FleurVideo.extract(new File("3dVideo.mp4"), 6000);

List<BufferedImage> splited = Fleur3dUtil.split(frame);
BufferedImage frameCombined = FleurFilter.additive(
        FleurFilter.color(splited.get(0), ColorMask.RED),
        FleurFilter.color(splited.get(1), ColorMask.GREEN_BLUE));

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
BufferedImage greenBlueFilterImg = FleurFilter.color(splited.get(1), FilterColor.GREEN_BLUE);
```


#### 5. Combined with filter
<img src="https://i.imgur.com/hgqPHa2.jpg" width="50%">
<p>
    Additive Color filtering => For every Pixel on final Image RGB(LeftImage.red, RightImage.green, RightImage.blue)
</p>

```java
BufferedImage additiveCombinedFrame = FleurFilter.additive(
        FleurFilter.color(splited.get(0), FilterColor.RED),
        FleurFilter.color(splited.get(1), FilterColor.GREEN_BLUE)
);
```

### 6. Compile to Video
<img src="https://github.com/Wetwer/movie-fleur/blob/master/img/gif_default.gif?raw=true" width="50%">

```java
// Extract images from Video (specific from frames 6000 - 6200)
List<BufferedImage> images
        = FleurVideo.extract(new File("3dVideo.mp4"), 6000, 6010);

List<BufferedImage> videoImages = new ArrayList<>();
for (BufferedImage image : images) {
    List<BufferedImage> splits = Fleur3dUtil.split(image);

    videoImages.add(FleurFilter.additive(
            FleurFilter.color(splits.get(0), ColorMask.RED),
            FleurFilter.color(splits.get(1), ColorMask.GREEN_BLUE)
    ));
}

FleurVideo.create(videoImages, "3dVideoOut.mp4");
```

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
FleurFilter.color(frame, ColorMask.GREEN)
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
            <th>Green & Blue</th>
            <td>
                <img src="https://i.imgur.com/hNGX1do.jpg" width="500px">
            </td>
            <td>
               <pre>
FleurFilter.color(frame, ColorMask.BLUE)
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
