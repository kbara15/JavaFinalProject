/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

/**
 * {@code VK_KHR_sampler_mirror_clamp_to_edge} extends the set of sampler address modes to include an additional mode ({@link #VK_SAMPLER_ADDRESS_MODE_MIRROR_CLAMP_TO_EDGE SAMPLER_ADDRESS_MODE_MIRROR_CLAMP_TO_EDGE}) that effectively uses a texture map twice as large as the original image in which the additional half of the new image is a mirror image of the original image.
 * 
 * <p>This new mode relaxes the need to generate images whose opposite edges match by using the original image to generate a matching "{@code mirror image}". This mode allows the texture to be mirrored only once in the negative s, t, and r directions.</p>
 * 
 * <h5>Example</h5>
 * 
 * <p>Creating a sampler with the new address mode in each dimension</p>
 * 
 * <pre><code>
 *     VkSamplerCreateInfo createInfo =
 *     {
 *         VK_STRUCTURE_TYPE_SAMPLER_CREATE_INFO // sType
 *         // Other members set to application-desired values
 *     };
 * 
 *     createInfo.addressModeU = VK_SAMPLER_ADDRESS_MODE_MIRROR_CLAMP_TO_EDGE;
 *     createInfo.addressModeV = VK_SAMPLER_ADDRESS_MODE_MIRROR_CLAMP_TO_EDGE;
 *     createInfo.addressModeW = VK_SAMPLER_ADDRESS_MODE_MIRROR_CLAMP_TO_EDGE;
 * 
 *     VkSampler sampler;
 *     VkResult result = vkCreateSampler(
 *         device,
 *         &amp;createInfo,
 *         &amp;sampler);</code></pre>
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_KHR_sampler_mirror_clamp_to_edge}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>15</dd>
 * <dt><b>Revision</b></dt>
 * <dd>3</dd>
 * <dt><b>Extension and Version Dependencies</b></dt>
 * <dd><ul>
 * <li>Requires Vulkan 1.0</li>
 * </ul></dd>
 * <dt><b>Contact</b></dt>
 * <dd><ul>
 * <li>Tobias Hector <a target="_blank" href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?title=VK_KHR_sampler_mirror_clamp_to_edge:%20&amp;body=@tobski%20">tobski</a></li>
 * </ul></dd>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2019-08-17</dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Tobias Hector, Imagination Technologies</li>
 * <li>Jon Leech, Khronos</li>
 * </ul></dd>
 * </dl>
 */
public final class KHRSamplerMirrorClampToEdge {

    /** The extension specification version. */
    public static final int VK_KHR_SAMPLER_MIRROR_CLAMP_TO_EDGE_SPEC_VERSION = 3;

    /** The extension name. */
    public static final String VK_KHR_SAMPLER_MIRROR_CLAMP_TO_EDGE_EXTENSION_NAME = "VK_KHR_sampler_mirror_clamp_to_edge";

    /**
     * Extends {@code VkSamplerAddressMode}.
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #VK_SAMPLER_ADDRESS_MODE_MIRROR_CLAMP_TO_EDGE SAMPLER_ADDRESS_MODE_MIRROR_CLAMP_TO_EDGE}</li>
     * <li>{@link #VK_SAMPLER_ADDRESS_MODE_MIRROR_CLAMP_TO_EDGE_KHR SAMPLER_ADDRESS_MODE_MIRROR_CLAMP_TO_EDGE_KHR}</li>
     * </ul>
     */
    public static final int
        VK_SAMPLER_ADDRESS_MODE_MIRROR_CLAMP_TO_EDGE     = 4,
        VK_SAMPLER_ADDRESS_MODE_MIRROR_CLAMP_TO_EDGE_KHR = 4;

    private KHRSamplerMirrorClampToEdge() {}

}