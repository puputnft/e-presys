package com.example.e_presys;

public class postSakit {
        private String id;
        private String matakuliah;
        private String dosen1;
        private String dosen2;
        private String dosen3;
        private String sakit;

        public postSakit(String id, String matakuliah, String dosen1,String dosen2,String dosen3){
            this.id = id;
            this.matakuliah = matakuliah;
            this.dosen1 = dosen1;
            this.dosen2 = dosen2;
            this.dosen3 = dosen3;
        }

    public String getSakit() {
        return sakit;
    }
}

