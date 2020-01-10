/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package openvr.templates

import org.lwjgl.generator.*
import openvr.*

val VROverlay = "VROverlay".nativeClass(
    Module.OPENVR,
    prefixMethod = "VROverlay_",
    binding = OPENVR_FNTABLE_BINDING
) {
    documentation = "Overlay management methods."
    nativeDirective("""
#ifdef LWJGL_WINDOWS
    #define APIENTRY __stdcall
#else
    #define APIENTRY
#endif

typedef struct HmdVector2_t
{
    float v[2];
} HmdVector2_t;

typedef struct HmdRect2_t
{
	struct HmdVector2_t vTopLeft;
	struct HmdVector2_t vBottomRight;
} HmdRect2_t;""")

    EVROverlayError(
        "FindOverlay",
        "Finds an existing overlay with the specified key.",

        charASCII.const.p("pchOverlayKey", ""),
        Check(1)..VROverlayHandle_t.p("pOverlayHandle", "")
    )

    EVROverlayError(
        "CreateOverlay",
        "Creates a new named overlay. All overlays start hidden and with default settings.",

        charASCII.const.p("pchOverlayKey", ""),
        charASCII.const.p("pchOverlayName", ""),
        Check(1)..VROverlayHandle_t.p("pOverlayHandle", "")
    )

    EVROverlayError(
        "DestroyOverlay",
        "Destroys the specified overlay. When an application calls #ShutdownInternal() all overlays created by that app are automatically destroyed.",

        VROverlayHandle_t("ulOverlayHandle", "")
    )

    EVROverlayError(
        "SetHighQualityOverlay",
        """
        Specify which overlay to use the high quality render path.

        This overlay will be composited in during the distortion pass which results in it drawing on top of everything else, but also at a higher quality as it
        samples the source texture directly rather than rasterizing into each eye's render texture first. Because if this, only one of these is supported at
        any given time. It is most useful for overlays that are expected to take up most of the user's view (e.g. streaming video). This mode does not support
        mouse input to your overlay.
        """,

        VROverlayHandle_t("ulOverlayHandle", "")
    )

    VROverlayHandle_t(
        "GetHighQualityOverlay",
        """
        Returns the overlay handle of the current overlay being rendered using the single high quality overlay render path. Otherwise it will return
        #k_ulOverlayHandleInvalid.
        """,
        void()
    )

    uint32_t(
        "GetOverlayKey",
        """
        Fills the provided buffer with the string key of the overlay. Returns the size of buffer required to store the key, including the terminating null
        character. #k_unVROverlayMaxKeyLength will be enough bytes to fit the string.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        Return(RESULT, includesNT = true)..nullable..charASCII.p("pchValue", ""),
        AutoSize("pchValue")..uint32_t("unBufferSize", ""),
        Check(1)..EVROverlayError.p("pError", "")
    )

    uint32_t(
        "GetOverlayName",
        """
        Fills the provided buffer with the friendly name of the overlay. Returns the size of buffer required to store the key, including the terminating null
        character. #k_unVROverlayMaxNameLength will be enough bytes to fit the string.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        Return(RESULT, includesNT = true)..nullable..charASCII.p("pchValue", ""),
        AutoSize("pchValue")..uint32_t("unBufferSize", ""),
        Check(1)..EVROverlayError.p("pError", "")
    )

    EVROverlayError(
        "SetOverlayName",
        "Sets the name to use for this overlay.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        charASCII.const.p("pchName", "")
    )

    EVROverlayError(
        "GetOverlayImageData",
        """
        Gets the raw image data from an overlay. Overlay image data is always returned as RGBA data, 4 bytes per pixel. If the buffer is not large enough,
        width and height will be set and #EVROverlayError_VROverlayError_ArrayTooSmall is returned.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        void.p("pvBuffer", ""),
        AutoSize("pvBuffer")..uint32_t("unBufferSize", ""),
        Check(1)..uint32_t.p("punWidth", ""),
        Check(1)..uint32_t.p("punHeight", "")
    )

    charASCII.const.p(
        "GetOverlayErrorNameFromEnum",
        "Returns a string that corresponds with the specified overlay error. The string will be the name of the error enum value for all valid error codes.",

        EVROverlayError("error", "", "EVROverlayError_\\w+")
    )

    EVROverlayError(
        "SetOverlayRenderingPid",
        """
        Sets the pid that is allowed to render to this overlay (the creator pid is always allow to render), by default this is the pid of the process that made
        the overlay.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        uint32_t("unPID", "")
    )

    uint32_t(
        "GetOverlayRenderingPid",
        "Gets the pid that is allowed to render to this overlay.",

        VROverlayHandle_t("ulOverlayHandle", "")
    )

    EVROverlayError(
        "SetOverlayFlag",
        "Specify flag setting for a given overlay.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        VROverlayFlags("eOverlayFlag", "", "VROverlayFlags_\\w+"),
        bool("bEnabled", "")
    )

    EVROverlayError(
        "GetOverlayFlag",
        "Sets flag setting for a given overlay.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        VROverlayFlags("eOverlayFlag", "", "VROverlayFlags_\\w+"),
        Check(1)..bool.p("pbEnabled", "")
    )

    EVROverlayError(
        "SetOverlayColor",
        "Sets the color tint of the overlay quad. Use 0.0 to 1.0 per channel.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        float("fRed", ""),
        float("fGreen", ""),
        float("fBlue", "")
    )

    EVROverlayError(
        "GetOverlayColor",
        "Gets the color tint of the overlay quad.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..float.p("pfRed", ""),
        Check(1)..float.p("pfGreen", ""),
        Check(1)..float.p("pfBlue", "")
    )

    EVROverlayError(
        "SetOverlayAlpha",
        "Sets the alpha of the overlay quad. Use 1.0 for 100 percent opacity to 0.0 for 0 percent opacity.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        float("fAlpha", "")
    )

    EVROverlayError(
        "GetOverlayAlpha",
        "Gets the alpha of the overlay quad. By default overlays are rendering at 100 percent alpha (1.0).",

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..float.p("pfAlpha", "")
    )

    EVROverlayError(
        "SetOverlayTexelAspect",
        """
        Sets the aspect ratio of the texels in the overlay. 1.0 means the texels are square. 2.0 means the texels are twice as wide as they are tall.

        Defaults to 1.0.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        float("fTexelAspect", "")
    )

    EVROverlayError(
        "GetOverlayTexelAspect",
        "Gets the aspect ratio of the texels in the overlay. Defaults to 1.0.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..float.p("pfTexelAspect", "")
    )

    EVROverlayError(
        "SetOverlaySortOrder",
        """
        Sets the rendering sort order for the overlay. Overlays are rendered this order:
        ${ul(
            "Overlays owned by the scene application",
            "Overlays owned by some other application"
        )}

        Within a category overlays are rendered lowest sort order to highest sort order. Overlays with the same sort order are rendered back to front base on
        distance from the HMD.

        Sort order defaults to 0.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        uint32_t("unSortOrder", "")
    )

    EVROverlayError(
        "GetOverlaySortOrder",
        "Gets the sort order of the overlay. See #SetOverlaySortOrder() for how this works.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..uint32_t.p("punSortOrder", "")
    )

    EVROverlayError(
        "SetOverlayWidthInMeters",
        "Sets the width of the overlay quad in meters. By default overlays are rendered on a quad that is 1 meter across.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        float("fWidthInMeters", "")
    )

    EVROverlayError(
        "GetOverlayWidthInMeters",
        "Returns the width of the overlay quad in meters. By default overlays are rendered on a quad that is 1 meter across.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..float.p("pfWidthInMeters", "")
    )

    EVROverlayError(
        "SetOverlayAutoCurveDistanceRangeInMeters",
        """
        For high-quality curved overlays only, sets the distance range in meters from the overlay used to automatically curve the surface around the viewer.
        Min is distance is when the surface will be most curved. Max is when least curved.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        float("fMinDistanceInMeters", ""),
        float("fMaxDistanceInMeters", "")
    )

    EVROverlayError(
        "GetOverlayAutoCurveDistanceRangeInMeters",
        """
        For high-quality curved overlays only, gets the distance range in meters from the overlay used to automatically curve the surface around the viewer.
        Min is distance is when the surface will be most curved. Max is when least curved.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..float.p("pfMinDistanceInMeters", ""),
        Check(1)..float.p("pfMaxDistanceInMeters", "")
    )

    EVROverlayError(
        "SetOverlayTextureColorSpace",
        """
        Sets the colorspace the overlay texture's data is in. Defaults to 'auto'. If the texture needs to be resolved, you should call #SetOverlayTexture()
        with the appropriate colorspace instead.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        EColorSpace("eTextureColorSpace", "", "EColorSpace_\\w+")
    )

    EVROverlayError(
        "GetOverlayTextureColorSpace",
        "Gets the overlay's current colorspace setting.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..EColorSpace.p("peTextureColorSpace", "")
    )

    EVROverlayError(
        "SetOverlayTextureBounds",
        "Sets the part of the texture to use for the overlay. UV Min is the upper left corner and UV Max is the lower right corner.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        VRTextureBounds_t.const.p("pOverlayTextureBounds", "")
    )

    EVROverlayError(
        "GetOverlayTextureBounds",
        "Gets the part of the texture to use for the overlay. UV Min is the upper left corner and UV Max is the lower right corner.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        VRTextureBounds_t.p("pOverlayTextureBounds", "")
    )

    uint32_t(
        "GetOverlayRenderModel",
        "Gets render model to draw behind this overlay.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        charASCII.p("pchValue", ""),
        AutoSize("pchValue")..uint32_t("unBufferSize", ""),
        HmdColor_t.p("pColor", ""),
        Check(1)..EVROverlayError.p("pError", "")
    )

    EVROverlayError(
        "SetOverlayRenderModel",
        "",

        VROverlayHandle_t("ulOverlayHandle", ""),
        charASCII.const.p("pchRenderModel", ""),
        HmdColor_t.p("pColor", "")
    )

    EVROverlayError(
        "GetOverlayTransformType",
        "Returns the transform type of this overlay.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..VROverlayTransformType.p("peTransformType", "")
    )

    EVROverlayError(
        "SetOverlayTransformAbsolute",
        "Sets the transform to absolute tracking origin.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        ETrackingUniverseOrigin("eTrackingOrigin", "", "ETrackingUniverseOrigin_\\w+"),
        HmdMatrix34_t.const.p("pmatTrackingOriginToOverlayTransform", "")
    )

    EVROverlayError(
        "GetOverlayTransformAbsolute",
        "Gets the transform if it is absolute. Returns an error if the transform is some other type.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..ETrackingUniverseOrigin.p("peTrackingOrigin", ""),
        HmdMatrix34_t.p("pmatTrackingOriginToOverlayTransform", "")
    )

    EVROverlayError(
        "SetOverlayTransformTrackedDeviceRelative",
        "Sets the transform to relative to the transform of the specified tracked device.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        TrackedDeviceIndex_t("unTrackedDevice", ""),
        HmdMatrix34_t.const.p("pmatTrackedDeviceToOverlayTransform", "")
    )

    EVROverlayError(
        "GetOverlayTransformTrackedDeviceRelative",
        "Gets the transform if it is relative to a tracked device. Returns an error if the transform is some other type.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..TrackedDeviceIndex_t.p("punTrackedDevice", ""),
        HmdMatrix34_t.p("pmatTrackedDeviceToOverlayTransform", "")
    )

    EVROverlayError(
        "SetOverlayTransformTrackedDeviceComponent",
        """
        Sets the transform to draw the overlay on a rendermodel component mesh instead of a quad. This will only draw when the system is drawing the device.
        Overlays with this transform type cannot receive mouse events.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        TrackedDeviceIndex_t("unDeviceIndex", ""),
        charASCII.const.p("pchComponentName", "")
    )

    EVROverlayError(
        "GetOverlayTransformTrackedDeviceComponent",
        "Gets the transform information when the overlay is rendering on a component.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..TrackedDeviceIndex_t.p("punDeviceIndex", ""),
        char.p("pchComponentName", ""),
        AutoSize("pchComponentName")..uint32_t("unComponentNameSize", "")
    )

    EVROverlayError(
        "GetOverlayTransformOverlayRelative",
        "",

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..VROverlayHandle_t.p("ulOverlayHandleParent", ""),
        HmdMatrix34_t.p("pmatParentOverlayToOverlayTransform", "")
    )

    EVROverlayError(
        "SetOverlayTransformOverlayRelative",
        "",

        VROverlayHandle_t("ulOverlayHandle", ""),
        VROverlayHandle_t("ulOverlayHandleParent", ""),
        HmdMatrix34_t.p("pmatParentOverlayToOverlayTransform", "")
    )

    EVROverlayError(
        "ShowOverlay",
        "Shows the VR overlay. For dashboard overlays, only the Dashboard Manager is allowed to call this.",

        VROverlayHandle_t("ulOverlayHandle", "")
    )

    EVROverlayError(
        "HideOverlay",
        "Hides the VR overlay. For dashboard overlays, only the Dashboard Manager is allowed to call this.",

        VROverlayHandle_t("ulOverlayHandle", "")
    )

    bool(
        "IsOverlayVisible",
        "Returns true if the overlay is visible.",

        VROverlayHandle_t("ulOverlayHandle", "")
    )

    EVROverlayError(
        "GetTransformForOverlayCoordinates",
        """
        Get the transform in 3d space associated with a specific 2d point in the overlay's coordinate space (where 0,0 is the lower left). -Z points out of the
        overlay.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        ETrackingUniverseOrigin("eTrackingOrigin", "", "ETrackingUniverseOrigin_\\w+"),
        HmdVector2_t("coordinatesInOverlay", ""),
        HmdMatrix34_t.p("pmatTransform", "")
    )

    bool(
        "PollNextOverlayEvent",
        """
        Returns true and fills the event with the next event on the overlay's event queue, if there is one.

        If there are no events this method returns false. {@code uncbVREvent} should be the size in bytes of the ##VREvent struct.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        VREvent_t.p("pEvent", ""),
        Expression("VREvent.SIZEOF")..uint32_t("uncbVREvent", "")
    )

    EVROverlayError(
        "GetOverlayInputMethod",
        "Returns the current input settings for the specified overlay.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..VROverlayInputMethod.p("peInputMethod", "")
    )

    EVROverlayError(
        "SetOverlayInputMethod",
        "Sets the input settings for the specified overlay.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        VROverlayInputMethod("eInputMethod", "", "VROverlayInputMethod_\\w+")
    )

    EVROverlayError(
        "GetOverlayMouseScale",
        """
        Gets the mouse scaling factor that is used for mouse events. The actual texture may be a different size, but this is typically the size of the
        underlying UI in pixels.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        HmdVector2_t.p("pvecMouseScale", "")
    )

    EVROverlayError(
        "SetOverlayMouseScale",
        """
        Sets the mouse scaling factor that is used for mouse events. The actual texture may be a different size, but this is typically the size of the
        underlying UI in pixels (not in world space).
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        HmdVector2_t.const.p("pvecMouseScale", "")
    )

    bool(
        "ComputeOverlayIntersection",
        """
        Computes the overlay-space pixel coordinates of where the ray intersects the overlay with the specified settings. Returns false if there is no
        intersection.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        VROverlayIntersectionParams_t.const.p("pParams", ""),
        VROverlayIntersectionResults_t.p("pResults", "")
    )

    bool(
        "IsHoverTargetOverlay",
        """
        Returns true if the specified overlay is the hover target. An overlay is the hover target when it is the last overlay "moused over" by the virtual
        mouse pointer.
        """,

        VROverlayHandle_t("ulOverlayHandle", "")
    )

    VROverlayHandle_t(
        "GetGamepadFocusOverlay",
        "Returns the current Gamepad focus overlay.",
        void()
    )

    EVROverlayError(
        "SetGamepadFocusOverlay",
        "Sets the current Gamepad focus overlay.",

        VROverlayHandle_t("ulNewFocusOverlay", "")
    )

    EVROverlayError(
        "SetOverlayNeighbor",
        """
        Sets an overlay's neighbor. This will also set the neighbor of the "to" overlay to point back to the "from" overlay. If an overlay's neighbor is set to
        invalid both ends will be cleared.
        """",

        EOverlayDirection("eDirection", "", "EOverlayDirection_\\w+"),
        VROverlayHandle_t("ulFrom", ""),
        VROverlayHandle_t("ulTo", "")
    )

    EVROverlayError(
        "MoveGamepadFocusToNeighbor",
        "Changes the Gamepad focus from one overlay to one of its neighbors.",

        EOverlayDirection("eDirection", "", "EOverlayDirection_\\w+"),
        VROverlayHandle_t("ulFrom", ""),

        returnDoc = "#EVROverlayError_VROverlayError_NoNeighbor if there is no neighbor in that direction"
    )

    EVROverlayError(
        "SetOverlayDualAnalogTransform",
        "Sets the analog input to Dual Analog coordinate scale for the specified overlay.",

        VROverlayHandle_t("ulOverlay", ""),
        EDualAnalogWhich("eWhich", "", "EDualAnalogWhich_\\w+"),
        HmdVector2_t.p("pvCenter", ""),
        float("fRadius", "")
    )

    EVROverlayError(
        "GetOverlayDualAnalogTransform",
        "Gets the analog input to Dual Analog coordinate scale for the specified overlay.",

        VROverlayHandle_t("ulOverlay", ""),
        EDualAnalogWhich("eWhich", "", "EDualAnalogWhich_\\w+"),
        HmdVector2_t.p("pvCenter", ""),
        Check(1)..float.p("pfRadius", "")
    )

    EVROverlayError(
        "SetOverlayTexture",
        "Texture to draw for the overlay. This function can only be called by the overlay's creator or renderer process (see #SetOverlayRenderingPid()).",

        VROverlayHandle_t("ulOverlayHandle", ""),
        Texture_t.const.p("pTexture", "")
    )

    EVROverlayError(
        "ClearOverlayTexture",
        "Use this to tell the overlay system to release the texture set for this overlay.",

        VROverlayHandle_t("ulOverlayHandle", "")
    )

    EVROverlayError(
        "SetOverlayRaw",
        """
        Separate interface for providing the data as a stream of bytes, but there is an upper bound on data that can be sent. This function can only be called
        by the overlay's renderer process.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        Unsafe..void.p("pvBuffer", ""),
        uint32_t("unWidth", ""),
        uint32_t("unHeight", ""),
        uint32_t("unDepth", "")
    )

    EVROverlayError(
        "SetOverlayFromFile",
        """
        Separate interface for providing the image through a filename: can be png or jpg, and should not be bigger than 1920x1080. This function can only be
        called by the overlay's renderer process
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        charASCII.const.p("pchFilePath", "")
    )

    EVROverlayError(
        "GetOverlayTexture",
        """
        Get the native texture handle/device for an overlay you have created.

        On windows this handle will be a ID3D11ShaderResourceView with a ID3D11Texture2D bound.

        The texture will always be sized to match the backing texture you supplied in SetOverlayTexture above.

        You MUST call #ReleaseNativeOverlayHandle() with {@code pNativeTextureHandle} once you are done with this texture.

        {@code pNativeTextureHandle} is an OUTPUT, it will be a pointer to a {@code ID3D11ShaderResourceView *}.
        {@code pNativeTextureRef} is an INPUT and should be a {@code ID3D11Resource *}. The device used by {@code pNativeTextureRef} will be used to bind
        {@code pNativeTextureHandle}.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..void.p.p("pNativeTextureHandle", ""),
        opaque_p("pNativeTextureRef", ""),
        Check(1)..uint32_t.p("pWidth", ""),
        Check(1)..uint32_t.p("pHeight", ""),
        Check(1)..uint32_t.p("pNativeFormat", ""),
        Check(1)..ETextureType.p("pAPIType", ""),
        Check(1)..EColorSpace.p("pColorSpace", ""),
        VRTextureBounds_t.p("pTextureBounds", "")
    )

    EVROverlayError(
        "ReleaseNativeOverlayHandle",
        """
        Release the {@code pNativeTextureHandle} provided from the #GetOverlayTexture() call, this allows the system to free the underlying GPU resources for
        this object, so only do it once you stop rendering this texture.
        """,

        VROverlayHandle_t("ulOverlayHandle", ""),
        opaque_p("pNativeTextureHandle", "")
    )

    EVROverlayError(
        "GetOverlayTextureSize",
        "Get the size of the overlay texture.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..uint32_t.p("pWidth", ""),
        Check(1)..uint32_t.p("pHeight", "")
    )

    EVROverlayError(
        "CreateDashboardOverlay",
        "Creates a dashboard overlay and returns its handle.",

        charASCII.const.p("pchOverlayKey", ""),
        charASCII.const.p("pchOverlayFriendlyName", ""),
        Check(1)..VROverlayHandle_t.p("pMainHandle", ""),
        Check(1)..VROverlayHandle_t.p("pThumbnailHandle", "")
    )

    bool(
        "IsDashboardVisible",
        "Returns true if the dashboard is visible.",
        void()
    )

    bool(
        "IsActiveDashboardOverlay",
        "Returns true if the dashboard is visible and the specified overlay is the active system Overlay.",

        VROverlayHandle_t("ulOverlayHandle", "")
    )

    EVROverlayError(
        "SetDashboardOverlaySceneProcess",
        "Sets the dashboard overlay to only appear when the specified process ID has scene focus.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        uint32_t("unProcessId", "")
    )

    EVROverlayError(
        "GetDashboardOverlaySceneProcess",
        "Gets the process ID that this dashboard overlay requires to have scene focus.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..uint32_t.p("punProcessId", "")
    )

    void(
        "ShowDashboard",
        "Shows the dashboard.",

        charASCII.const.p("pchOverlayToShow", "")
    )

    TrackedDeviceIndex_t(
        "GetPrimaryDashboardDevice",
        "Returns the tracked device that has the laser pointer in the dashboard.",
        void()
    )

    EVROverlayError(
        "ShowKeyboard",
        "Show the virtual keyboard to accept input.",

        EGamepadTextInputMode("eInputMode", "", "EGamepadTextInputMode_\\w+"),
        EGamepadTextInputLineMode("eLineInputMode", "", "EGamepadTextInputLineMode_\\w+"),
        charASCII.const.p("pchDescription", ""),
        uint32_t("unCharMax", ""),
        charASCII.const.p("pchExistingText", ""),
        bool("bUseMinimalMode", ""),
        uint64_t("uUserValue", "")
    )

    EVROverlayError(
        "ShowKeyboardForOverlay",
        "",

        VROverlayHandle_t("ulOverlayHandle", ""),
        EGamepadTextInputMode("eInputMode", "", "EGamepadTextInputMode_\\w+"),
        EGamepadTextInputLineMode("eLineInputMode", "", "EGamepadTextInputLineMode_\\w+"),
        charASCII.const.p("pchDescription", ""),
        uint32_t("unCharMax", ""),
        charASCII.const.p("pchExistingText", ""),
        bool("bUseMinimalMode", ""),
        uint64_t("uUserValue", "")
    )

    uint32_t(
        "GetKeyboardText",
        "Get the text that was entered into the text input.",

        Return(RESULT, includesNT = true)..nullable..charASCII.p("pchText", ""),
        AutoSize("pchText")..uint32_t("cchText", "")
    )

    void(
        "HideKeyboard",
        "Hide the virtual keyboard."
    )

    void(
        "SetKeyboardTransformAbsolute",
        "Set the position of the keyboard in world space.",

        ETrackingUniverseOrigin("eTrackingOrigin", "", "ETrackingUniverseOrigin_\\w+"),
        HmdMatrix34_t.const.p("pmatTrackingOriginToKeyboardTransform", "")
    )

    void(
        "SetKeyboardPositionForOverlay",
        "Set the position of the keyboard in overlay space by telling it to avoid a rectangle in the overlay. Rectangle coords have (0,0) in the bottom left.",

        VROverlayHandle_t("ulOverlayHandle", ""),
        HmdRect2_t("avoidRect", "")
    )

    EVROverlayError(
        "SetOverlayIntersectionMask",
        "Sets a list of primitives to be used for controller ray intersection typically the size of the underlying UI in pixels(not in world space).",

        VROverlayHandle_t("ulOverlayHandle", ""),
        VROverlayIntersectionMaskPrimitive_t.p("pMaskPrimitives", ""),
        AutoSize("pMaskPrimitives")..uint32_t("unNumMaskPrimitives", ""),
        Expression("VROverlayIntersectionMaskPrimitive.SIZEOF")..uint32_t("unPrimitiveSize", "")
    )

    EVROverlayError(
        "GetOverlayFlags",
        "",

        VROverlayHandle_t("ulOverlayHandle", ""),
        Check(1)..uint32_t.p("pFlags", "")
    )

    VRMessageOverlayResponse(
        "ShowMessageOverlay",
        "Show the message overlay. This will block and return you a result.",

        charASCII.const.p("pchText", ""),
        charASCII.const.p("pchCaption", ""),
        charASCII.const.p("pchButton0Text", ""),
        nullable..charASCII.const.p("pchButton1Text", ""),
        nullable..charASCII.const.p("pchButton2Text", ""),
        nullable..charASCII.const.p("pchButton3Text", "")
    )

    void(
        "CloseMessageOverlay",
        "If the calling process owns the overlay and it's open, this will close it."
    )
}