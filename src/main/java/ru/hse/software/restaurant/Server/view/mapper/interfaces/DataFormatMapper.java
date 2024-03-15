package ru.hse.software.restaurant.Server.view.mapper.interfaces;

public interface DataFormatMapper<Format, Obj> {
    Format inFormat(Obj object) throws Exception;
    Obj inObject(Format format, Class<Obj> obj) throws Exception;
}
