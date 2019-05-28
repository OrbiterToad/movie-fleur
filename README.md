# movie-fleur 🌻
[![emoji-log](https://cdn.rawgit.com/ahmadawais/stuff/ca97874/emoji-log/flat-round.svg)](https://github.com/ahmadawais/Emoji-Log/)
[![CircleCI](https://circleci.com/gh/Wetwer/movie-fleur/tree/master.svg?style=svg)](https://circleci.com/gh/Wetwer/movie-fleur/tree/master)

Convert 3D movies with splited Frames to Anaglyph 3D movies to watch with 3d Red (Blue/Cyan/Green) Glasses

### Convert from video file with given framePosition
If a 3D video is available the frames can be extracted from the Video by applying the frame Position. 3D video Files can be found here: <a href="https://yts.am/browse-movies/0/3D/all/0/downloads">YTS</a>
```java
VideoBuilder videoBuilder = VideoBuilder.builder().inputFile("3dVideo.mp4").build();

BufferedImage frame = new VideoConverter(videoBuilder).getFrame(6000);
List<BufferedImage> splited = ImageSplitter.split(frame);
BufferedImage frameCombined = AdditiveCombiner.combine(
        ColorFilter.colorMask(splited.get(0), FilterColor.RED),
        ColorFilter.colorMask(splited.get(1), FilterColor.GREEN_BLUE));

ImageHelper.saveImage(frameCombined, "frame_combined.png");
```


#### 1. Extract frames from video file
<img src="https://mask.imgur.com/ODNFPZ6.jpg" width="50%">

```java
VideoBuilder videoBuilder = VideoBuilder.builder().inputFile("3dVideo.mp4").build();

BufferedImage frame = new VideoConverter(videoBuilder).getFrame(6000);
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
List<BufferedImage> splited = ImageSplitter.split(frame);
```

#### 3. Combine Frames to one image (No color filtering)
<img src="https://mask.imgur.com/lFv7n8y.jpg" width="50%">

```java
BufferedImage combined = AlphaCombiner.combine(frame);
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
BufferedImage redFilterImg = ColorFilter.colorMask(splited.get(0), FilterColor.RED);
BufferedImage greenBlueFilterImg = ColorFilter.colorMask(splited.get(1), FilterColor.GREEN_BLUE);
```


#### 5. Combined with filter
<img src="https://i.imgur.com/hgqPHa2.jpg" width="50%">
<p>
    Additive Color filtering => For every Pixel on final Image RGB(LeftImage.red, RightImage.green, RightImage.blue)
</p>

```java
BufferedImage additiveCombinedFrame = AdditiveCombiner.combine(
        ColorFilter.colorMask(splited.get(0), FilterColor.RED),
        ColorFilter.colorMask(splited.get(1), FilterColor.GREEN_BLUE)
);
```

#### Apply Grayscale to images

<table>
<tr>
<th>Default</th>
<th>Indexed</th>
<th>Gray</th>
<th>Binary</th>
</tr>
<tr>
<td>
<img src="https://i.imgur.com/ZcNK1s2.jpg">
</td>
<td>
<img src="https://i.imgur.com/dBI2ZFI.png">
</td>
<td>
<img src="https://i.imgur.com/C6A2pMO.jpg">
</td>
<td>
<img src="https://i.imgur.com/FyijEmt.png">
</td>
</tr>

<tr>
<th>Green</th>
<th>Blue</th>
<th>Transparent (0.5)</th>
<th>Invert</th>
</tr>
<tr>
<td>
<img src="https://i.imgur.com/3HPSel1.png">
</td>
<td>
<img src="https://i.imgur.com/lXIyDEm.jpg">
</td>
<td>
<img src="https://i.imgur.com/zMNBL6G.jpg">
</td>
<td>
<img src="https://i.imgur.com/RXWopYs.jpg">
</td>
</tr>
</table>
