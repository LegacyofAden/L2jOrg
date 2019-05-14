package org.l2j.gameserver.network.serverpackets.captcha;

import org.l2j.gameserver.network.L2GameClient;
import org.l2j.gameserver.network.OutgoingPackets;
import org.l2j.gameserver.network.serverpackets.IClientOutgoingPacket;

import java.nio.ByteBuffer;

public class ReceiveBotCaptchaImage extends IClientOutgoingPacket {


    @Override
    protected void writeImpl(L2GameClient client, ByteBuffer packet) throws Exception {
        OutgoingPackets.RECEIVE_BOT_CAPTCHA_IMAGE.writeId(packet);
        packet.put((byte) 0x02); // unk
        packet.putLong(0x02);   // unk
        packet.putInt(1200); // time
        var bytes = new byte[] {
                0x44 ,0x44 ,0x53 ,0x20, // image file type
                0x7C ,0x00 ,0x00 ,0x00 // structure size
                ,0x07 ,0x10 ,0x00 ,0x00 // flags
                ,0x20 ,0x00 ,0x00 ,0x00, // height px
                Integer.decode("0x80").byteValue() ,0x00 ,0x00 ,0x00 , // width px
                0x00 ,0x00 ,0x00 ,0x00, // Pitch Or Linear Size
                0x00 ,0x00 ,0x00 ,0x00, // Depth
                0x00 ,0x00 ,0x00 ,0x00, // MipMapCount
                0x00 ,0x00 ,0x00 ,0x00, // Reserved1[1]
                0x00 ,0x00 ,0x00 ,0x00, // Reserved1[2]
                0x00 ,0x00 ,0x00 ,0x00, // Reserved1[3]
                0x00 ,0x00 ,0x00 ,0x00, // Reserved1[4]
                0x00 ,0x00 ,0x00 ,0x00, // Reserved1[5]
                0x00 ,0x00 ,0x00 ,0x00, // Reserved1[6]
                0x00 ,0x00 ,0x00 ,0x00, // Reserved1[7]
                0x00 ,0x00 ,0x00 ,0x00, // Reserved1[8]
                0x00 ,0x00 ,0x00 ,0x00, // Reserved1[9]
                0x00 ,0x00 ,0x00 ,0x00, // Reserved1[10]
                0x00 ,0x00 ,0x00 ,0x00, // Reserved1[11]

                // Pixel Format
                0x20 ,0x00 ,0x00 ,0x00, // Scructure size
                0x04 ,0x00 ,0x00 ,0x00, // Flags
                0x44 ,0x58 ,0x54 ,0x31, // Format DXT
                0x00 ,0x00 ,0x00 ,0x00, // RGB bit count
                0x00 ,0x00 ,0x00 ,0x00, // Red bit mask
                0x00 ,0x00 ,0x00 ,0x00, // Green bit mask
                0x00 ,0x00 ,0x00 ,0x00, // Blue bit mask
                0x00 ,0x00 ,0x00 ,0x00, // Alpah bit mask
                // End pixel format
                0x00 ,0x10 ,0x00 ,0x00, // Complexity Caps
                0x00 ,0x00 ,0x00 ,0x00, // Additional Detail Caps 2
                0x00 ,0x00 ,0x00 ,0x00, // Caps 3
                0x00 ,0x00 ,0x00 ,0x00, // Caps 4
                0x00 ,0x00 ,0x00 ,0x00, // Reserved 2

                // DATA
                0x61 ,0x08 ,0x42 ,0x00 ,0x00 ,Integer.decode("0xFC").byteValue() ,Integer.decode("0xFC").byteValue() ,Integer.decode("0xFC").byteValue() ,0x42 ,0x00 ,Integer.decode("0xAE").byteValue() ,0x73 ,0x00 ,0x00 ,0x00 ,0x40 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,0x55 ,0x55 ,0x55 ,0x0A ,Integer.decode("0x86").byteValue() ,0x29 ,0x42 ,0x08 ,0x09 ,0x55 ,0x55 ,0x54 ,0x42 ,0x08 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x00 ,Integer.decode("0x98").byteValue() ,Integer.decode("0x98").byteValue() ,0x00 ,0x42 ,0x19 ,0x42 ,0x08 ,0x55 ,0x55 ,0x55 ,0x25 ,Integer.decode("0x81").byteValue() ,0x10 ,0x42 ,0x08 ,0x2A ,Integer.decode("0xF5").byteValue() ,Integer.decode("0xF5").byteValue() ,Integer.decode("0xF5").byteValue() ,0x29 ,0x7A ,0x42 ,0x08 ,0x3F ,0x55 ,0x55 ,0x55 ,0x42 ,0x08 ,0x29 ,0x7A ,Integer.decode("0x95").byteValue() ,0x00 ,0x00 ,0x00 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,0x55 ,Integer.decode("0xED").byteValue() ,Integer.decode("0x89").byteValue() ,0x75 ,0x61 ,0x10 ,0x42 ,0x08 ,0x00 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x35 ,0x01 ,0x01 ,0x7D ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x00 ,0x00 ,Integer.decode("0x96").byteValue() ,Integer.decode("0x95").byteValue() ,0x42 ,0x10 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x15 ,0x15 ,0x15 ,0x15 ,0x42 ,0x10 ,Integer.decode("0xA3").byteValue() ,0x18 ,0x55 ,0x00 ,0x00 ,0x00 ,Integer.decode("0x82").byteValue() ,0x10 ,0x42 ,0x10 ,0x08 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x55 ,0x55 ,Integer.decode("0x9F").byteValue() ,0x10 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,Integer.decode("0xFD").byteValue() ,0x55 ,0x5C ,0x58 ,0x42 ,0x10 ,0x49 ,0x4A ,0x55 ,0x00 ,0x00 ,0x00 ,Integer.decode("0xA4").byteValue() ,0x53 ,0x42 ,0x10 ,0x7A ,0x55 ,0x55 ,0x3D ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x55 ,0x55 ,0x5E ,0x58 ,Integer.decode("0xC3").byteValue() ,0x18 ,0x42 ,0x10 ,Integer.decode("0xA0").byteValue() ,0x55 ,0x55 ,0x55 ,0x2F ,Integer.decode("0xEC").byteValue() ,0x42 ,0x10 ,0x55 ,0x55 ,Integer.decode("0xA5").byteValue() ,0x25 ,0x10 ,Integer.decode("0xEC").byteValue() ,0x63 ,0x18 ,0x25 ,0x09 ,0x00 ,0x00 ,0x10 ,Integer.decode("0xEC").byteValue() ,0x63 ,0x10 ,0x5C ,0x58 ,0x58 ,0x58 ,Integer.decode("0xC2").byteValue() ,0x20 ,0x63 ,0x10 ,0x20 ,0x55 ,0x55 ,0x55 ,Integer.decode("0x82").byteValue() ,0x10 ,0x63 ,0x18 ,0x00 ,0x55 ,0x55 ,0x55 ,Integer.decode("0xA3").byteValue() ,0x18 ,0x63 ,0x18 ,0x2A ,0x55 ,0x15 ,0x15 ,0x63 ,0x18 , Integer.decode("0xA6").byteValue() ,Integer.decode("0xAF").byteValue(),0x00 ,0x00 ,0x05 ,0x05 ,0x43 ,0x21 ,0x63 ,0x18 ,0x2F ,0x55 ,0x55 ,0x55 ,Integer.decode("0x83").byteValue() ,0x29 ,0x63 ,0x18 ,Integer.decode("0xF0").byteValue() ,0x55 ,0x55 ,0x55 ,Integer.decode("0x82").byteValue() ,0x10 ,0x63 ,0x18 ,0x00 ,0x55 ,0x55 ,0x55 ,0x42 ,0x00 ,Integer.decode("0x82").byteValue() ,0x10 ,0x02 ,0x02 ,0x02 ,0x01 ,Integer.decode("0xBE").byteValue() ,Integer.decode("0xF7").byteValue() ,0x42 ,0x00 ,0x35 ,0x05 ,0x25 ,0x25 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x00 ,0x00 ,Integer.decode("0xFE").byteValue() ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,Integer.decode("0x80").byteValue() ,0x00 ,Integer.decode("0xAB").byteValue() ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x04 ,0x21 ,0x7E ,Integer.decode("0xC0").byteValue() ,0x20 ,0x32 ,Integer.decode("0xA6").byteValue() ,Integer.decode("0xA7").byteValue() ,0x42 ,0x08 ,0x05 ,Integer.decode("0xA5").byteValue() ,0x56 ,0x56 ,Integer.decode("0xE5").byteValue() ,Integer.decode("0x85").byteValue() ,0x42 ,0x08 ,0x55 ,Integer.decode("0x95").byteValue() ,0x15 ,0x15 ,Integer.decode("0xE1").byteValue() ,Integer.decode("0xEF").byteValue() ,0x42 ,0x08 ,0x25 ,0x08 ,0x20 ,0x10 ,Integer.decode("0xE0").byteValue() ,Integer.decode("0xFF").byteValue() ,0x02 ,0x21 ,Integer.decode("0xFA").byteValue() ,0x02 ,Integer.decode("0xBA").byteValue() ,0x62 ,Integer.decode("0xE0").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,Integer.decode("0xD5").byteValue() ,0x3C ,0x00 ,0x00 ,0x44 ,0x75 ,0x42 ,0x08 ,0x55 ,0x54 ,0x56 ,0x57 ,Integer.decode("0xA6").byteValue() ,Integer.decode("0xA7").byteValue() ,0x42 ,0x08 ,0x15 ,0x15 ,Integer.decode("0xD5").byteValue() ,Integer.decode("0xD5").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,Integer.decode("0xD4").byteValue() ,Integer.decode("0xD6").byteValue() ,Integer.decode("0xD8").byteValue() ,0x5C ,Integer.decode("0x8A").byteValue() ,0x52 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x15 ,0x15 ,Integer.decode("0x95").byteValue() ,0x55 ,0x42 ,0x10 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x00 ,0x00 ,0x02 ,0x01 ,0x42 ,0x10 ,0x42 ,0x10 ,0x00 ,0x00 ,0x00 ,0x00 ,Integer.decode("0x85").byteValue() ,0x7D ,0x42 ,0x10 ,Integer.decode("0x92").byteValue() ,0x55 ,0x55 ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x56 ,Integer.decode("0xAD").byteValue() ,0x09 ,Integer.decode("0x89").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,Integer.decode("0xB5").byteValue() ,0x00 ,0x00 ,Integer.decode("0xAA").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xAB").byteValue() ,0x5A ,Integer.decode("0xC3").byteValue() ,Integer.decode("0xC0").byteValue() ,0x40 ,Integer.decode("0xC0").byteValue() ,Integer.decode("0xA6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x5E ,Integer.decode("0xD5").byteValue() ,Integer.decode("0x95").byteValue() ,0x3C ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x55 ,0x56 ,0x5C ,Integer.decode("0x96").byteValue() ,Integer.decode("0x96").byteValue() ,Integer.decode("0xD7").byteValue() ,0x42 ,0x10 ,Integer.decode("0xD5").byteValue() ,0x55 ,0x5F ,0x50 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,Integer.decode("0xA0").byteValue() ,Integer.decode("0x89").byteValue() ,Integer.decode("0xA5").byteValue() ,Integer.decode("0xB5").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x63 ,0x10 ,0x5A ,0x70 ,0x5A ,Integer.decode("0x8A").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x63 ,0x10 ,0x55 ,0x72 ,0x73 ,0x55 ,0x63 ,0x18 ,0x63 ,0x18 ,0x00 ,0x00 ,0x00 ,0x00 ,0x63 ,0x18 ,Integer.decode("0xA6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x00 ,0x00 ,0x00 ,0x50 ,Integer.decode("0xFB").byteValue() ,Integer.decode("0xDE").byteValue() ,0x63 ,0x18 ,0x55 ,0x55 ,0x55 ,0x35 ,0x63 ,0x18 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x00 ,0x00 ,Integer.decode("0xAA").byteValue() ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x63 ,0x18 ,0x55 ,0x55 ,0x57 ,0x70 ,0x24 ,0x53 ,0x63 ,0x18 ,0x55 ,0x15 ,Integer.decode("0xD5").byteValue() ,0x55 ,0x42 ,0x00 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,Integer.decode("0x80").byteValue() ,0x60 ,Integer.decode("0x80").byteValue() ,0x00 ,Integer.decode("0xBE").byteValue() ,Integer.decode("0xF7").byteValue() ,0x42 ,0x00 ,Integer.decode("0x85").byteValue() ,0x56 ,0x57 ,0x55 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,0x55 ,0x55 ,Integer.decode("0x8D").byteValue() ,Integer.decode("0x8D").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,Integer.decode("0x95").byteValue() ,0x35 ,Integer.decode("0x8D").byteValue() ,Integer.decode("0xC2").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,Integer.decode("0xC8").byteValue() ,Integer.decode("0x8A").byteValue() ,Integer.decode("0x8D").byteValue() ,0x55 ,Integer.decode("0xA3").byteValue() ,0x53 ,0x42 ,0x08 ,0x55 ,0x55 ,0x55 ,0x52 ,Integer.decode("0xE0").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x15 ,0x15 ,0x15 ,Integer.decode("0xD5").byteValue() ,Integer.decode("0xE0").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x58 ,0x50 ,0x70 ,0x5A ,0x42 ,0x08 ,Integer.decode("0xA3").byteValue() ,0x3A ,0x24 ,0x00 ,0x00 ,0x00 ,0x01 ,0x42 ,Integer.decode("0xE0").byteValue() ,Integer.decode("0xFF").byteValue() ,0x56 ,Integer.decode("0x96").byteValue() ,0x16 ,Integer.decode("0x95").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,0x56 ,0x55 ,0x05 ,0x05 ,Integer.decode("0xC5").byteValue() ,Integer.decode("0x96").byteValue() ,0x42 ,0x08 ,0x55 ,0x55 ,0x55 ,0x58 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,0x50 ,Integer.decode("0xC0").byteValue() ,Integer.decode("0xC9").byteValue() ,Integer.decode("0xE3").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xE7").byteValue() ,Integer.decode("0xAF").byteValue() ,0x58 ,Integer.decode("0x82").byteValue() ,Integer.decode("0x82").byteValue() ,0x02 ,0x07 ,Integer.decode("0x9F").byteValue() ,0x42 ,0x10 ,0x06 ,0x05 ,0x55 ,Integer.decode("0xA2").byteValue() ,0x42 ,0x10 ,0x31 ,Integer.decode("0x84").byteValue() ,0x00 ,0x00 ,0x00 ,0x55 ,0x18 ,Integer.decode("0xC6").byteValue() ,0x42 ,0x10 ,0x55 ,0x55 ,0x55 ,0x0A ,0x18 ,Integer.decode("0xC6").byteValue() ,0x42 ,0x10 ,0x15 ,0x35 ,Integer.decode("0xD5").byteValue() ,0x00 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,Integer.decode("0x97").byteValue() ,Integer.decode("0x96").byteValue() ,0x17 ,0x2A ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xC3").byteValue() ,0x31 ,Integer.decode("0x80").byteValue() ,0x40 ,0x20 ,0x00 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x08 ,Integer.decode("0xC9").byteValue() ,0x76 ,Integer.decode("0xE8").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x36 ,Integer.decode("0x95").byteValue() ,0x55 ,Integer.decode("0x85").byteValue() ,Integer.decode("0xFE").byteValue() ,Integer.decode("0xF7").byteValue() ,0x42 ,0x10 ,0x70 ,0x63 ,0x49 ,Integer.decode("0x8D").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,Integer.decode("0x95").byteValue() ,Integer.decode("0xAD").byteValue() ,Integer.decode("0x89").byteValue() ,0x25 ,Integer.decode("0xEB").byteValue() ,Integer.decode("0xCD").byteValue() ,0x63 ,0x10 ,0x0A ,0x7A ,0x7A ,0x68 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x63 ,0x10 ,0x55 ,Integer.decode("0x95").byteValue() ,0x15 ,Integer.decode("0xD5").byteValue() ,Integer.decode("0xA6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x63 ,0x18 ,0x55 ,0x56 ,0x54 ,0x57 ,0x63 ,0x18 ,0x65 ,Integer.decode("0xA7").byteValue() ,0x50 ,0x00 ,0x00 ,0x00 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xA4").byteValue() ,0x20 ,0x0D ,0x01 ,0x03 ,0x02 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x63 ,0x18 ,0x00 ,Integer.decode("0xB8").byteValue() ,0x57 ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x46 ,0x31 ,Integer.decode("0xC0").byteValue() ,0x00 ,0x03 ,0x01 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x63 ,0x18 ,Integer.decode("0xEC").byteValue() ,Integer.decode("0x88").byteValue() ,0x77 ,0x57 ,0x61 ,0x08 ,0x42 ,0x00 ,Integer.decode("0xFC").byteValue() ,Integer.decode("0xFC").byteValue() ,Integer.decode("0xFC").byteValue() ,Integer.decode("0xFC").byteValue() ,Integer.decode("0xEB").byteValue() ,Integer.decode("0xBF").byteValue() ,0x42 ,0x00 ,Integer.decode("0xB5").byteValue() ,0x0D ,0x05 ,Integer.decode("0xB5").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x75 ,Integer.decode("0xFF").byteValue() ,0x00 ,0x2E ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x62 ,Integer.decode("0xA0").byteValue() ,0x00 ,Integer.decode("0xA8").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x55 ,Integer.decode("0xAA").byteValue() ,0x00 ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x60 ,0x00 ,Integer.decode("0xA8").byteValue() ,0x55 ,0x42 ,0x08 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x00 ,0x55 ,Integer.decode("0x95").byteValue() ,0x16 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,Integer.decode("0xFF").byteValue() ,0x00 ,Integer.decode("0xAA").byteValue() ,Integer.decode("0xC1").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,Integer.decode("0xAF").byteValue() ,0x00 ,0x2F ,0x0D ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x00 ,0x00 ,0x5C ,0x56 ,Integer.decode("0xFA").byteValue() ,Integer.decode("0xEF").byteValue() ,0x42 ,0x08 ,0x28 ,0x00 ,0x41 ,0x79 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x00 ,Integer.decode("0xAA").byteValue() ,0x55 ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x00 ,Integer.decode("0xAA").byteValue() ,0x55 ,0x57 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0x8A").byteValue() ,0x52 ,0x00 ,0x02 ,0x01 ,0x01 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x00 ,Integer.decode("0xFF").byteValue() ,0x57 ,0x57 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x00 ,Integer.decode("0xFF").byteValue() ,0x55 ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x00 ,0x5B ,0x72 ,0x79 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x00 ,0x50 ,0x5F ,Integer.decode("0xB5").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x00 ,0x35 ,0x25 ,0x02 ,Integer.decode("0x8A").byteValue() ,0x52 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x55 ,Integer.decode("0xA5").byteValue() ,0x55 ,0x25 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x00 ,0x00 ,0x78 ,0x55 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x0E ,0x55 ,0x55 ,0x55 ,Integer.decode("0xFC").byteValue() ,Integer.decode("0xEF").byteValue() ,0x42 ,0x10 ,0x09 ,0x09 ,0x15 ,Integer.decode("0xD5").byteValue() ,0x76 ,Integer.decode("0xD7").byteValue() ,0x42 ,0x10 ,0x35 ,Integer.decode("0xD6").byteValue() ,Integer.decode("0xD8").byteValue() ,Integer.decode("0xE0").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x63 ,0x10 ,0x7A ,0x62 ,0x62 ,0x6A ,0x63 ,0x10 ,0x63 ,0x18 ,Integer.decode("0xAA").byteValue() ,Integer.decode("0xAA").byteValue() ,Integer.decode("0xAA").byteValue() ,Integer.decode("0xAA").byteValue() ,0x63 ,0x18 ,0x63 ,0x18 ,0x00 ,0x00 ,0x00 ,0x00 ,0x63 ,0x18 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x00 ,Integer.decode("0x80").byteValue() ,0x40 ,Integer.decode("0x80").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x63 ,0x18 ,Integer.decode("0x80").byteValue() ,0x57 ,0x54 ,Integer.decode("0xD7").byteValue() ,Integer.decode("0xD7").byteValue() ,Integer.decode("0xBD").byteValue() ,0x63 ,0x18 ,0x55 ,0x55 ,0x56 ,0x30 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0x86").byteValue() ,0x4A ,0x01 ,0x03 ,0x02 ,Integer.decode("0x80").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x63 ,0x18 ,Integer.decode("0xD6").byteValue() ,0x1A ,Integer.decode("0xD0").byteValue() ,0x5F ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x00 ,Integer.decode("0xD5").byteValue() ,0x25 ,Integer.decode("0xB5").byteValue() ,0x55 ,0x42 ,0x00 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x60 ,0x42 ,0x40 ,0x40 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0x83").byteValue() ,0x10 ,0x06 ,0x0E ,0x00 ,0x02 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,0x70 ,0x58 ,0x57 ,0x55 ,0x42 ,0x08 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x00 ,0x00 ,0x00 ,0x26 ,0x24 ,0x5C ,0x42 ,0x08 ,0x15 ,Integer.decode("0x95").byteValue() ,0x55 ,0x55 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,0x58 ,0x5C ,0x55 ,Integer.decode("0xD5").byteValue() ,Integer.decode("0xE0").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x7D ,Integer.decode("0xD5").byteValue() ,0x35 ,0x08 ,Integer.decode("0xA0").byteValue() ,Integer.decode("0xF7").byteValue() ,0x42 ,0x08 ,0x03 ,0x70 ,0x5C ,0x07 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,0x57 ,0x55 ,Integer.decode("0xD5").byteValue() ,0x25 ,0x42 ,0x08 ,0x63 ,0x4B ,0x00 ,0x00 ,0x00 ,0x01 ,0x31 ,Integer.decode("0x84").byteValue() ,0x42 ,0x08 ,0x15 ,Integer.decode("0xD5").byteValue() ,0x55 ,0x15 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x58 ,0x5E ,0x55 ,0x0A ,Integer.decode("0xC4").byteValue() ,0x18 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x54 ,0x54 ,0x54 ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x56 ,0x56 ,0x00 ,0x00 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x55 ,0x2B ,Integer.decode("0x80").byteValue() ,0x40 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,Integer.decode("0x83").byteValue() ,0x00 ,0x7E ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x00 ,Integer.decode("0xFA").byteValue() ,0x55 ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x08 ,0x02 ,0x0B ,0x05 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x50 ,0x50 ,Integer.decode("0xD0").byteValue() ,0x58 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x55 ,0x5E ,Integer.decode("0x88").byteValue() ,Integer.decode("0x8F").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x55 ,Integer.decode("0xDF").byteValue() ,Integer.decode("0xD0").byteValue() ,0x50 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x57 ,0x58 ,0x50 ,0x5E ,Integer.decode("0xFE").byteValue() ,Integer.decode("0xF7").byteValue() ,0x42 ,0x10 ,0x40 ,Integer.decode("0xA2").byteValue() ,0x03 ,Integer.decode("0xC9").byteValue() ,0x10 ,Integer.decode("0xEC").byteValue() ,Integer.decode("0xC7").byteValue() ,0x61 ,0x40 ,Integer.decode("0xC0").byteValue() ,Integer.decode("0xC0").byteValue() ,Integer.decode("0x80").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x63 ,0x10 ,0x5D ,0x72 ,0x7B ,Integer.decode("0x8D").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x63 ,0x18 ,0x55 ,0x55 ,0x3F ,0x00 ,0x63 ,0x18 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x00 ,0x00 ,0x01 ,0x01 ,Integer.decode("0x83").byteValue() ,0x31 ,0x63 ,0x18 ,0x15 ,Integer.decode("0xD5").byteValue() ,0x55 ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x63 ,0x18 ,0x02 ,0x00 ,0x2A ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x63 ,0x18 ,0x60 ,0x70 ,Integer.decode("0xC0").byteValue() ,Integer.decode("0x80").byteValue() ,0x63 ,0x18 ,Integer.decode("0xA4").byteValue() ,0x20 ,0x00 ,0x00 ,0x00 ,0x01 ,0x42 ,0x00 ,Integer.decode("0x84").byteValue() ,0x7D ,0x00 ,0x00 ,0x00 ,0x50 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x00 ,0x15 ,Integer.decode("0xD5").byteValue() ,0x55 ,Integer.decode("0xD5").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x6E ,0x6B ,Integer.decode("0xC2").byteValue() ,Integer.decode("0xC2").byteValue() ,0x40 ,0x40 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,Integer.decode("0xD5").byteValue() ,0x15 ,0x15 ,0x55 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,0x73 ,0x5C ,0x70 ,0x5A ,Integer.decode("0x84").byteValue() ,0x7D ,0x42 ,0x08 ,0x55 ,0x55 ,0x55 ,0x52 ,Integer.decode("0xE0").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,Integer.decode("0xD5").byteValue() ,Integer.decode("0x95").byteValue() ,0x35 ,0x0D ,Integer.decode("0xE0").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x00 ,Integer.decode("0x80").byteValue() ,0x56 ,0x2A ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x06 ,0x55 ,Integer.decode("0xBD").byteValue() ,0x00 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,Integer.decode("0xB5").byteValue() ,Integer.decode("0xE1").byteValue() ,0x02 ,Integer.decode("0xE0").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x57 ,0x0A ,0x00 ,0x37 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x2B ,0x00 ,Integer.decode("0xDE").byteValue() ,0x56 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x00 ,0x00 ,0x5F ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x00 ,Integer.decode("0xAA").byteValue() ,0x55 ,0x55 ,Integer.decode("0xF2").byteValue() ,Integer.decode("0xD7").byteValue() ,0x42 ,0x10 ,0x00 ,Integer.decode("0x8A").byteValue() ,0x55 ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x60 ,Integer.decode("0xC9").byteValue() ,0x49 ,0x55 ,0x03 ,0x31 ,0x02 ,0x00 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x5A ,0x70 ,Integer.decode("0xFF").byteValue() ,0x05 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x05 ,0x0D ,0x0D ,0x09 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x58 ,0x5C ,Integer.decode("0xDC").byteValue() ,0x2C ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x55 ,0x55 ,0x58 ,0x40 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x55 ,0x6D ,Integer.decode("0xC9").byteValue() ,0x75 ,0x00 ,0x00 ,Integer.decode("0xA5").byteValue() ,0x28 ,Integer.decode("0xAA").byteValue() ,Integer.decode("0xAA").byteValue() ,Integer.decode("0xAA").byteValue() ,Integer.decode("0xAA").byteValue() ,Integer.decode("0xFE").byteValue() ,Integer.decode("0xF7").byteValue() ,0x42 ,0x10 ,Integer.decode("0x8D").byteValue() ,0x02 ,0x02 ,0x35 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,Integer.decode("0xA3").byteValue() ,0x18 ,0x55 ,0x7E ,0x62 ,Integer.decode("0xFA").byteValue() ,0x65 ,Integer.decode("0xA7").byteValue() ,0x63 ,0x10 ,Integer.decode("0xCD").byteValue() ,0x50 ,0x50 ,0x5B ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x63 ,0x18 ,Integer.decode("0x82").byteValue() ,0x63 ,0x55 ,0x55 ,0x63 ,0x18 ,Integer.decode("0xFB").byteValue() ,Integer.decode("0xDE").byteValue() ,0x00 ,0x40 ,0x40 ,0x40 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x63 ,0x18 ,0x55 ,0x60 ,0x60 ,Integer.decode("0x80").byteValue() ,0x63 ,0x18 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x00 ,0x28 ,0x14 ,0x28 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x4A ,0x52 ,0x03 ,0x01 ,0x01 ,0x03 ,0x31 ,Integer.decode("0x8C").byteValue() ,0x63 ,0x18 ,0x56 ,Integer.decode("0xD4").byteValue() ,0x54 ,0x56 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x00 ,0x05 ,Integer.decode("0xD5").byteValue() ,0x55 ,0x55 ,0x42 ,0x00 ,Integer.decode("0xC4").byteValue() ,0x18 ,0x41 ,0x00 ,0x00 ,0x00 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x40 ,Integer.decode("0xFB").byteValue() ,0x55 ,0x55 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,0x5F ,0x50 ,0x2E ,0x25 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x72 ,Integer.decode("0xA2").byteValue() ,0x0F ,0x35 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,Integer.decode("0xF0").byteValue() ,0x02 ,Integer.decode("0xE8").byteValue() ,0x50 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x02 ,0x00 ,Integer.decode("0xF7").byteValue() ,Integer.decode("0xD5").byteValue() ,0x42 ,0x08 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x55 ,0x55 ,Integer.decode("0xAA").byteValue() ,0x05 ,Integer.decode("0xF6").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x20 ,0x00 ,Integer.decode("0xAA").byteValue() ,0x55 ,Integer.decode("0xE0").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x08 ,0x50 ,0x50 ,Integer.decode("0xDA").byteValue() ,0x35 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,Integer.decode("0x8D").byteValue() ,Integer.decode("0x8D").byteValue() ,0x57 ,0x54 ,0x00 ,0x00 ,Integer.decode("0xA4").byteValue() ,0x18 ,Integer.decode("0xAA").byteValue() ,Integer.decode("0xAA").byteValue() ,Integer.decode("0xAA").byteValue() ,Integer.decode("0xAA").byteValue() ,0x42 ,0x08 ,0x42 ,0x10 ,Integer.decode("0xAA").byteValue() ,Integer.decode("0xAA").byteValue() ,Integer.decode("0xAA").byteValue() ,Integer.decode("0xAA").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,Integer.decode("0x95").byteValue() ,0x35 ,Integer.decode("0xD5").byteValue() ,0x55 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x57 ,0x56 ,0x25 ,0x05 ,Integer.decode("0xFE").byteValue() ,Integer.decode("0xF7").byteValue() ,0x42 ,0x10 ,Integer.decode("0x8D").byteValue() ,0x2D ,0x57 ,0x57 ,Integer.decode("0xFE").byteValue() ,Integer.decode("0xF7").byteValue() ,0x42 ,0x10 ,0x1E ,0x00 ,0x0B ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x55 ,0x15 ,0x28 ,0x09 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xAF").byteValue() ,0x7B ,0x0D ,0x00 ,0x00 ,0x20 ,Integer.decode("0xE4").byteValue() ,0x5B ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0x85").byteValue() ,0x25 ,0x55 ,0x55 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x42 ,0x10 ,0x63 ,0x7E ,0x40 ,0x40 ,0x42 ,0x10 ,0x42 ,0x10 ,0x00 ,0x00 ,0x00 ,0x00 ,0x42 ,0x10 ,Integer.decode("0xA4").byteValue() ,0x42 ,0x00 ,0x00 ,0x00 ,0x40 ,Integer.decode("0xBE").byteValue() ,Integer.decode("0xEF").byteValue() ,0x42 ,0x10 ,0x15 ,Integer.decode("0x95").byteValue() ,Integer.decode("0xD5").byteValue() ,Integer.decode("0xD6").byteValue() ,Integer.decode("0xFE").byteValue() ,Integer.decode("0xF7").byteValue() ,0x63 ,0x10 ,0x26 ,Integer.decode("0xB4").byteValue() ,0x1C ,Integer.decode("0x9C").byteValue() ,0x63 ,0x18 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x05 ,0x0A ,0x01 ,0x02 ,Integer.decode("0xA6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x63 ,0x18 ,0x55 ,0x55 ,0x25 ,0x05 ,0x63 ,0x18 ,0x14 ,Integer.decode("0xA5").byteValue() ,0x40 ,0x00 ,0x00 ,0x00 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x63 ,0x18 ,Integer.decode("0x80").byteValue() ,0x00 ,0x03 ,0x0D ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,0x63 ,0x18 ,0x55 ,0x3E ,0x00 ,0x00 ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xFF").byteValue() ,Integer.decode("0xC5").byteValue() ,0x28 ,0x00 ,Integer.decode("0x80").byteValue() ,0x40 ,Integer.decode("0xC0").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x63 ,0x18 ,0x49 ,Integer.decode("0x89").byteValue() ,0x0D ,Integer.decode("0xC0").byteValue() ,0x61 ,0x08 ,0x42 ,0x00 ,Integer.decode("0xFC").byteValue() ,Integer.decode("0xFC").byteValue() ,Integer.decode("0xFC").byteValue() ,Integer.decode("0xFC").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x00 ,0x55 ,Integer.decode("0xB5").byteValue() ,0x25 ,Integer.decode("0xD5").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,0x55 ,Integer.decode("0xC3").byteValue() ,0x62 ,0x55 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,0x5E ,0x50 ,0x5F ,0x55 ,0x42 ,0x08 ,0x44 ,0x64 ,0x40 ,0x00 ,0x00 ,0x00 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,Integer.decode("0xE0").byteValue() ,0x09 ,Integer.decode("0xE3").byteValue() ,0x73 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,0x55 ,0x55 ,0x75 ,Integer.decode("0x85").byteValue() ,Integer.decode("0x85").byteValue() ,Integer.decode("0x8E").byteValue() ,0x42 ,0x08 ,0x5E ,0x55 ,0x55 ,0x63 ,0x04 ,0x6D ,0x42 ,0x08 ,Integer.decode("0x95").byteValue() ,Integer.decode("0xD5").byteValue() ,0x55 ,0x35 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,Integer.decode("0x98").byteValue() ,0x5C ,0x57 ,0x58 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,0x57 ,Integer.decode("0xC9").byteValue() ,Integer.decode("0xC9").byteValue() ,0x55 ,Integer.decode("0x84").byteValue() ,0x64 ,0x42 ,0x08 ,0x55 ,0x15 ,Integer.decode("0x95").byteValue() ,0x55 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x08 ,0x57 ,0x5C ,0x5C ,0x55 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x55 ,Integer.decode("0xB5").byteValue() ,0x05 ,Integer.decode("0xB5").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x25 ,Integer.decode("0xE3").byteValue() ,0x62 ,0x57 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x42 ,0x10 ,0x57 ,0x55 ,0x35 ,0x35 ,0x42 ,0x10 ,Integer.decode("0xE4").byteValue() ,0x5B ,0x00 ,0x00 ,0x01 ,0x01 ,0x45 ,0x7D ,0x42 ,0x10 ,Integer.decode("0x95").byteValue() ,0x55 ,0x55 ,0x15 ,Integer.decode("0xFE").byteValue() ,Integer.decode("0xF7").byteValue() ,0x42 ,0x10 ,0x02 ,Integer.decode("0xB5").byteValue() ,0x55 ,0x5C ,Integer.decode("0xFE").byteValue() ,Integer.decode("0xF7").byteValue() ,0x42 ,0x10 ,Integer.decode("0xA8").byteValue() ,0x00 ,0x0D ,0x55 ,Integer.decode("0xFE").byteValue() ,Integer.decode("0xF7").byteValue() ,0x42 ,0x10 ,0x6A ,0x7A ,0x00 ,Integer.decode("0xAD").byteValue() ,Integer.decode("0xFA").byteValue() ,Integer.decode("0xE7").byteValue() ,0x42 ,0x10 ,0x55 ,0x55 ,Integer.decode("0xBA").byteValue() ,0x00 ,Integer.decode("0xD6").byteValue() ,Integer.decode("0xC6").byteValue() ,0x42 ,0x10 ,Integer.decode("0x95").byteValue() ,0x57 ,0x5C ,0x00 ,Integer.decode("0xFE").byteValue() ,Integer.decode("0xF7").byteValue() ,0x42 ,0x10 ,Integer.decode("0xDC").byteValue() ,Integer.decode("0xD7").byteValue() ,Integer.decode("0x95").byteValue() ,0x0A ,Integer.decode("0xFE").byteValue() ,Integer.decode("0xF7").byteValue() ,0x63 ,0x10 ,0x5C ,0x5C ,0x5C ,0x56 ,0x63 ,0x18 ,0x63 ,0x18 ,0x00 ,0x00 ,0x00 ,0x00 ,0x43 ,0x29 ,0x63 ,0x18 ,0x25 ,0x55 ,0x55 ,0x55 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x63 ,0x18 ,Integer.decode("0x8D").byteValue() ,Integer.decode("0x8D").byteValue() ,0x55 ,0x55 ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x63 ,0x18 ,Integer.decode("0x95").byteValue() ,Integer.decode("0xDD").byteValue() ,0x63 ,0x73 ,0x18 ,Integer.decode("0xC6").byteValue() ,0x63 ,0x18 ,Integer.decode("0x80").byteValue() ,Integer.decode("0x96").byteValue() ,0x15 ,Integer.decode("0xD5").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x03 ,0x3A ,Integer.decode("0xCB").byteValue() ,Integer.decode("0x82").byteValue() ,0x00 ,Integer.decode("0xDA").byteValue() ,Integer.decode("0xE6").byteValue() ,Integer.decode("0xAF").byteValue() ,0x63 ,0x18 ,0x60 ,0x57 ,0x57 ,0x55
        };
        packet.put(bytes);
    }
}
