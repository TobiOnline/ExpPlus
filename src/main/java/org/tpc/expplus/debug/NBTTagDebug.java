package org.tpc.expplus.debug;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;

public class NBTTagDebug {
	
	private static String nl = System.getProperty("line.separator");

	public static String debug(NBTBase tag) {
		StringBuilder sb = new StringBuilder();
		debug(tag, sb);
		sb.append(nl);
		return sb.toString();
	}
	
	public static void debug(NBTBase tag, StringBuilder sb) {
		debug(tag, sb, 0);
	}
	
	private static void debug(NBTBase tag, StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(tag.getClass().getSimpleName());
		
		if(tag instanceof NBTTagCompound) {
			
			sb.append(" {");
			sb.append(nl);
			boolean start = true;
			
			for(String subTag : ((NBTTagCompound)tag).getKeySet()) {
				if(!start) {
					sb.append(nl);
				}
				start = false;
				debug(((NBTTagCompound)tag).getTag(subTag), sb, indent + 1);
			}
			
			sb.append(nl);
			indent(sb, indent);
			sb.append("}");
			
		} else if(tag instanceof NBTTagList) {
			
			sb.append(" {");
			sb.append(nl);
			
			for(int subTag = 0; subTag < ((NBTTagList)tag).tagCount(); subTag++) {
				if(subTag != 0) {
					sb.append(nl);
				}
				debug(((NBTTagList)tag).get(subTag), sb, indent + 1);
			}
			
			sb.append(nl);
			indent(sb, indent);
			sb.append("}");
			
		} else if(tag instanceof NBTTagByte) {
			
			sb.append(": ");
			sb.append(((NBTTagByte)tag).getByte());
			
		} else if(tag instanceof NBTTagDouble) {
			
			sb.append(": ");
			sb.append(((NBTTagDouble)tag).getDouble());
			
		} else if(tag instanceof NBTTagFloat) {
			
			sb.append(": ");
			sb.append(((NBTTagFloat)tag).getFloat());
			
		} else if(tag instanceof NBTTagInt) {
			
			sb.append(": ");
			sb.append(((NBTTagInt)tag).getInt());
			
		} else if(tag instanceof NBTTagLong) {
			
			sb.append(": ");
			sb.append(((NBTTagLong)tag).getLong());
			
		} else if(tag instanceof NBTTagShort) {
			
			sb.append(": ");
			sb.append(((NBTTagShort)tag).getShort());
			
		} else if(tag instanceof NBTTagString) {
			
			sb.append(": ");
			sb.append(((NBTTagString)tag).getString());
			
		} else if(tag instanceof NBTTagByteArray) {
				
				sb.append(": ");
				
				sb.append("[");
				sb.append(new String(((NBTTagByteArray)tag).getByteArray()));
				sb.append("]");
				
				sb.append("[");
				sb.append(nl);
				boolean start = true;
				for(byte b : ((NBTTagByteArray)tag).getByteArray()) {
					if(!start) {
						sb.append(nl);
					}
					start = false;
					indent(sb, indent + 1);
					sb.append(b);
				}
				sb.append("]");
				
				
		} else if(tag instanceof NBTTagIntArray) {
			
			sb.append(": ");
			
			sb.append("[");
			boolean start = true;
			for(int i : ((NBTTagIntArray)tag).getIntArray()) {
				if(!start) {
					sb.append(nl);
				}
				start = false;
				indent(sb, indent + 1);
				sb.append(i);
			}
			sb.append("]");
			
		}
	}
	
	private static void indent(StringBuilder sb, int amount) {
		for(; amount > 0; amount--) {
			sb.append("   ");
		}
	}
	
}
