# movie-fleur 🌻

Convert 3D movies with split frames to Anaglyph 3D movies for viewing with red (blue/cyan/green) 3D glasses.

## Convert from Video File with Frame Position

If a 3D video is available, frames can be extracted by specifying the frame position. 3D video files can be
found [here](https://yts.mx/browse-movies/0/3D/all/0/latest/0/all).

```java
BufferedImage frame = FleurVideo.extract(new File("3dVideo.mp4"), 6000);

List<BufferedImage> splited = Fleur3dUtil.split(frame);
BufferedImage frameCombined = FleurFilter.additive(
        FleurFilter.color(splited.get(0), ColorMask.RED),
        FleurFilter.color(splited.get(1), ColorMask.CYAN));

ImageHelper.saveImage(frameCombined, "frame_combined.png");
```

## Steps to Convert

### 1. Extract Frames from Video File

![Extract Frames](https://mask.imgur.com/ODNFPZ6.jpg)

```java
FleurVideo.extract(new File("3dVideo.mp4"), 6000);
```

### 2. Split and Stretch Frames

| Left Frame                                        | Right Frame                                        |
|---------------------------------------------------|----------------------------------------------------|
| ![Left Frame](https://mask.imgur.com/S2jKdVA.jpg) | ![Right Frame](https://mask.imgur.com/krLVxOF.jpg) |

```java
List<BufferedImage> splited = Fleur3dUtil.split(frame);
```

### 3. Combine Frames without Color Filtering

![Combined Frame](https://mask.imgur.com/lFv7n8y.jpg)

```java
BufferedImage combined = FleurFilter.alphaCombine(frame1, frame2);
```

### 4. Apply Color Filters

| Left Frame with Red Filter                        | Right Frame with Cyan Filter                    |
|---------------------------------------------------|-------------------------------------------------|
| ![Red Filter](https://mask.imgur.com/YKJuLNS.png) | ![Cyan Filter](https://i.imgur.com/hNGX1do.jpg) |

```java
BufferedImage redFilterImg = FleurFilter.color(splited.get(0), FilterColor.RED);
BufferedImage greenBlueFilterImg = FleurFilter.color(splited.get(1), FilterColor.CYAN);
```

### 5. Combine Frames with Additive Filtering

![Filtered Combined Frame](https://i.imgur.com/hgqPHa2.jpg)

```java
BufferedImage additiveCombinedFrame = FleurFilter.additive(
        FleurFilter.color(splited.get(0), FilterColor.RED),
        FleurFilter.color(splited.get(1), FilterColor.CYAN)
);
```

### 6. Compile to Video

![Compiled Video](https://github.com/Wetwer/movie-fleur/blob/master/demo/gif_default.gif)

```java
List<BufferedImage> images = FleurVideo.extract(new File("3dVideo.mp4"), 6000, 6010);
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

### 7. Compile to Polarized 3D Video (Left/Right)

| Left Video                                                                         | Right Video                                                                          |
|------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| ![Left Video](https://github.com/Wetwer/movie-fleur/blob/master/demo/gif_left.gif) | ![Right Video](https://github.com/Wetwer/movie-fleur/blob/master/demo/gif_right.gif) |

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

To view: play both videos on separate projectors or use active shutter 3D glasses on a 60Hz monitor.

## Further Filters

| Filter Name                | Example                                           | Code Snippet                                                                                                                    |
|----------------------------|---------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|
| **Default**                | ![Default](https://i.imgur.com/ZcNK1s2.jpg)       | _Default rendering_                                                                                                             |
| **Additive Blue Yellow**   | ![Blue Yellow](https://i.imgur.com/skpQs5x.jpg)   | `FleurFilter.additive(FleurFilter.color(splits.get(0), ColorMask.BLUE), FleurFilter.color(splits.get(1), ColorMask.YELLOW));`   |
| **Additive Green Magenta** | ![Green Magenta](https://i.imgur.com/Hr3l2PD.jpg) | `FleurFilter.additive(FleurFilter.color(splits.get(0), ColorMask.GREEN), FleurFilter.color(splits.get(1), ColorMask.MAGENTA));` |
| **Indexed**                | ![Indexed](https://i.imgur.com/dBI2ZFI.png)       | `ImageHelper.convertToType(frame, BufferedImage.TYPE_BYTE_INDEXED)`                                                             |
| **Red**                    | ![Red](https://mask.imgur.com/YKJuLNS.png)        | `FleurFilter.color(frame, ColorMask.RED)`                                                                                       |
| **Green**                  | ![Green](https://i.imgur.com/P84E62j.png)         | `FleurFilter.color(frame, ColorMask.GREEN)`                                                                                     |
| **Blue**                   | ![Blue](https://i.imgur.com/lXIyDEm.jpg)          | `FleurFilter.color(frame, ColorMask.BLUE)`                                                                                      |
| **Cyan**                   | ![Cyan](https://i.imgur.com/hNGX1do.jpg)          | `FleurFilter.color(frame, ColorMask.CYAN)`                                                                                      |
| **Gray**                   | ![Gray](https://i.imgur.com/C6A2pMO.jpg)          | `FleurFilter.gray(frame)`                                                                                                       |
| **Binary**                 | ![Binary](https://i.imgur.com/FyijEmt.png)        | `ImageHelper.convertToType(frame, BufferedImage.TYPE_BYTE_BINARY)`                                                              |
| **Transparent (0.5)**      | ![Transparent](https://i.imgur.com/zMNBL6G.jpg)   | `FleurFilter.transparent(frame, 0.5)`                                                                                           |
| **Invert**                 | ![Invert](https://i.imgur.com/RXWopYs.jpg)        | `FleurFilter.invert(frame)`                                                                                                     | 
