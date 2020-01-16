import kotlinx.coroutines.async
import kotlinx.coroutines.await
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import styled.*
import kotlin.browser.window

interface VideoPlayerProps: RProps {
    var video: Video
    var onWatchedButtonPressed: (Video) -> Unit
    var unwatchedVideo: Boolean
}

class VideoPlayer(props: VideoPlayerProps): RComponent<VideoPlayerProps, RState>(props) {
    override fun RBuilder.render() {
        styledDiv {
            css {
                position = Position.absolute
                top = 10.px
                right = 10.px
            }
            h3 {
                +"${props.video.speaker}: ${props.video.title}"
            }
            styledButton {
                css {
                    display = Display.block
                    backgroundColor = if(props.unwatchedVideo) Color.lightGreen else Color.red
                    color = if(props.unwatchedVideo) Color.black else Color.white
                    fontSize = 12.pt
                    fontFamily = "Arial"
                    padding = "10pt"
                    marginBottom = 10.pt
                    border = "none"
                }
                attrs {
                    onClickFunction = {
                        props.onWatchedButtonPressed(props.video)
                    }
                }
                if(props.unwatchedVideo) {
                    +"Mark as watched"
                }
                else {
                    +"Mark as unwatched"
                }
            }
            styledDiv {
                css {
                    display = Display.flex
                    marginBottom = 10.px
                }
                EmailShareButton {
                    attrs.url = props.video.videoUrl
                    EmailIcon {
                        attrs.size = 32
                        attrs.round = true
                    }
                }
                FacebookShareButton {
                    attrs.url = props.video.videoUrl
                    FacebookIcon {
                        attrs.size = 32
                        attrs.round = true
                    }
                }
                TelegramShareButton {
                    attrs.url = props.video.videoUrl
                    TelegramIcon {
                        attrs.size = 32
                        attrs.round = true
                    }
                }
            }
            ReactPlayer {
                attrs.url = props.video.videoUrl
            }
        }
    }
}

fun RBuilder.videoPlayer(handler: VideoPlayerProps.() -> Unit): ReactElement {
    return child(VideoPlayer::class) {
        this.attrs(handler)
    }
}
