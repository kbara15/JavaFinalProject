/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val KHR_imageless_framebuffer = "KHRImagelessFramebuffer".nativeClassVK("KHR_imageless_framebuffer", type = "device", postfix = KHR) {
    documentation =
        """
        This extension allows framebuffers to be created without the need for creating images first, allowing more flexibility in how they are used, and avoiding the need for many of the confusing compatibility rules.

        Framebuffers are now created with a small amount of additional metadata about the image views that will be used in ##VkFramebufferAttachmentsCreateInfoKHR, and the actual image views are provided at render pass begin time via ##VkRenderPassAttachmentBeginInfoKHR.

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_KHR_imageless_framebuffer}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Device extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>109</dd>

            <dt><b>Revision</b></dt>
            <dd>1</dd>

            <dt><b>Extension and Version Dependencies</b></dt>
            <dd><ul>
                <li>Requires Vulkan 1.0</li>
                <li>Requires {@link KHRMaintenance2 VK_KHR_maintenance2}</li>
                <li>Requires {@link KHRImageFormatList VK_KHR_image_format_list}</li>
            </ul></dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Tobias Hector <a target="_blank" href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?title=VK_KHR_imageless_framebuffer:%20&amp;body=@tobias%20">tobias</a></li>
            </ul></dd>

            <dt>Last Modified Date</dt>
            <dd>2018-12-14</dd>

            <dt>Contributors</dt>
            <dd><ul>
                <li>Tobias Hector</li>
                <li>Graham Wihlidal</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "KHR_IMAGELESS_FRAMEBUFFER_SPEC_VERSION".."1"
    )

    StringConstant(
        "The extension name.",

        "KHR_IMAGELESS_FRAMEBUFFER_EXTENSION_NAME".."VK_KHR_imageless_framebuffer"
    )

    EnumConstant(
        "Extends {@code VkStructureType}.",

        "STRUCTURE_TYPE_PHYSICAL_DEVICE_IMAGELESS_FRAMEBUFFER_FEATURES_KHR".."1000108000",
        "STRUCTURE_TYPE_FRAMEBUFFER_ATTACHMENTS_CREATE_INFO_KHR".."1000108001",
        "STRUCTURE_TYPE_FRAMEBUFFER_ATTACHMENT_IMAGE_INFO_KHR".."1000108002",
        "STRUCTURE_TYPE_RENDER_PASS_ATTACHMENT_BEGIN_INFO_KHR".."1000108003"
    )

    EnumConstant(
        "Extends {@code VkFramebufferCreateFlagBits}.",

        "FRAMEBUFFER_CREATE_IMAGELESS_BIT_KHR".enum(0x00000001)
    )
}