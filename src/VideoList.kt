import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import kotlin.browser.window

class VideoList(props: VideoListProps): RComponent<VideoListProps, RState>(props) {
    override fun RBuilder.render() {
        for (video in props.videos) {
            p {
                key = video.id.toString()
                attrs {
                    onClickFunction = {
                        //window.alert("Clicked $video!")
                        setState {
                            props.onSelectVideo(video)
                        }
                    }
                }
                if(video == props.selectedVideo) {
                    +"▶ "
                }
                +"${video.speaker}: ${video.title}"
            }
        }
    }
}

interface VideoListProps: RProps {
    var videos: List<Video>
    var selectedVideo: Video?
    var onSelectVideo: (video: Video) -> Unit
}

fun RBuilder.videoList(handler: VideoListProps.() -> Unit): ReactElement {
    return child(VideoList::class) {
        this.attrs(handler)
    }
}
